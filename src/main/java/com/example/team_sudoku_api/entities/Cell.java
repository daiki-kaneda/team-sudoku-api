package com.example.team_sudoku_api.entities;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cell extends BaseEntity<Cell.CellId> {
    @EmbeddedId
    private CellId id;

    @ManyToOne
    @JoinColumn(name = "board_id", referencedColumnName = "id")
    @MapsId("boardId")
    private Board board;

    @Min(value = 1)
    @Max(value = 9)
    @Column(name = "cell_value")
    private Integer value;

    @Min(value = 1)
    @Max(value = 9)
    private int correctValue;

    public void setBoard(Board board) {
        this.board = board;
    }

    public static Cell create(CellId id,Board board,Integer value,int correctValue){
        Cell cell = new Cell();
        cell.id=id;
        cell.board=board;
        cell.value=value;
        cell.correctValue=correctValue;
        return cell;
    }

    @Data
    @Embeddable
    public static class CellId implements Serializable {
        @Column(name = "board_id")
        private String boardId;

        @Column(name = "row_idx")
        @Min(value = 0)
        @Max(value = 8)
        private int row;

        @Column(name = "col_idx")
        @Min(value = 0)
        @Max(value = 8)
        private int column;

        public static CellId create(String boardId,int row,int column){
            CellId id = new CellId();
            id.boardId=boardId;
            id.row=row;
            id.column=column;
            return id;
        }
    }

    @Override
    public CellId getId() {
        return id;
    }
}
