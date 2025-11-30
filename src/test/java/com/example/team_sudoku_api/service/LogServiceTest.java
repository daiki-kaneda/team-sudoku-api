package com.example.team_sudoku_api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.team_sudoku_api.controllers.dto.SolvedCellDTO;
import com.example.team_sudoku_api.entities.Cell;
import com.example.team_sudoku_api.entities.Log;
import com.example.team_sudoku_api.entities.User;
import com.example.team_sudoku_api.entities.UserTeam;
import com.example.team_sudoku_api.repositories.CellQueryRepository;
import com.example.team_sudoku_api.repositories.LogRepository;
import com.example.team_sudoku_api.repositories.UserTeamQueryRepository;
import com.example.team_sudoku_api.services.LogService;

@ExtendWith(MockitoExtension.class)
public class LogServiceTest {

    @Mock
    private LogRepository logRepository;
    @Mock
    private UserTeamQueryRepository userTeamQueryRepository;
    @Mock
    private CellQueryRepository cellQueryRepository;

    @InjectMocks
    private LogService logService;

    @Test
    @DisplayName("同じセルへの重複ログがある場合、最新（リストの後ろ）のものが採用されること")
    void getTeamBoardState_LatestWins() {
        // Arrange (準備)
        String teamId = "team-1";

        // モックデータの作成
        // (0,0) に "5" を入れたログ (古い)
        Log log1 = createMockLog(0, 0, 5, LocalDateTime.of(2025, 1, 1, 10, 1));
        // (0,0) に "9" を入れたログ (新しい) -> ★これが採用されるべき！
        Log log2 = createMockLog(0, 0, 9, LocalDateTime.of(2025, 1, 1, 10, 5));
        // (0,1) に "3" を入れたログ (別のセル)
        Log log3 = createMockLog(0, 1, 3, LocalDateTime.of(2025, 1, 1, 10, 2));

        // Repositoryがこのリストを返すように設定（昇順で返ってくる前提）
        when(logRepository.findByTeamIdAndSuccessResult(teamId))
                .thenReturn(List.of(log1, log3, log2));

        // Act (実行)
        List<SolvedCellDTO> result = logService.getTeamBoardStateByTeamId(teamId);

        // Assert (検証)
        assertEquals(2, result.size(), "セルは2箇所だけ埋まっているはず");

        // (0,0) の値を確認
        SolvedCellDTO cell00 = result.stream()
                .filter(d -> d.row() == 0 && d.column() == 0)
                .findFirst().orElseThrow();

        assertEquals(9, cell00.value(), "新しいログの値(9)で上書きされていること");
    }

    // テスト用ヘルパーメソッド（Logのモック作成）
    private Log createMockLog(int row, int col, int value, LocalDateTime time) {
        Log log = mock(Log.class);
        Cell cell = mock(Cell.class);
        Cell.CellId cellId = mock(Cell.CellId.class);
        UserTeam userTeam = mock(UserTeam.class);
        User user = mock(User.class);

        // チェーンメソッドのモック化 (LombokのGetterなどを想定)
        when(log.getCell()).thenReturn(cell);
        when(cell.getId()).thenReturn(cellId);
        when(cellId.getRow()).thenReturn(row);
        when(cellId.getColumn()).thenReturn(col);
        when(cell.getCorrectValue()).thenReturn(value); // 成功ログなので正解=入力値

        when(log.getUserTeam()).thenReturn(userTeam);
        when(userTeam.getUser()).thenReturn(user);
        when(user.getName()).thenReturn("TestUser");

        when(log.getCreatedAt()).thenReturn(time);

        return log;
    }
}
