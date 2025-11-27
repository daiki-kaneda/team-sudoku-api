INSERT INTO board (id) VALUES ("board1")

INSERT INTO cell (board_id, row_idx, col_idx, cell_value, correct_value) VALUES
-- Row 0 (1行目)
('board1', 0, 0, NULL, 9),
('board1', 0, 1, 8, 8),
('board1', 0, 2, NULL, 2),
('board1', 0, 3, NULL, 6),
('board1', 0, 4, NULL, 7),
('board1', 0, 5, NULL, 5),
('board1', 0, 6, NULL, 1),
('board1', 0, 7, NULL, 3),
('board1', 0, 8, NULL, 4),

-- Row 1 (2行目)
('board1', 1, 0, NULL, 7),
('board1', 1, 1, NULL, 4),
('board1', 1, 2, NULL, 3),
('board1', 1, 3, 9, 9),
('board1', 1, 4, NULL, 1),
('board1', 1, 5, NULL, 8),
('board1', 1, 6, 2, 2),
('board1', 1, 7, NULL, 5),
('board1', 1, 8, NULL, 6),

-- Row 2 (3行目)
('board1', 2, 0, 6, 6),
('board1', 2, 1, NULL, 5),
('board1', 2, 2, 1, 1),
('board1', 2, 3, NULL, 2),
('board1', 2, 4, 3, 3),
('board1', 2, 5, NULL, 4),
('board1', 2, 6, NULL, 7),
('board1', 2, 7, 8, 8),
('board1', 2, 8, NULL, 9),

-- Row 3 (4行目)
('board1', 3, 0, NULL, 2),
('board1', 3, 1, NULL, 9),
('board1', 3, 2, 4, 4),
('board1', 3, 3, NULL, 7),
('board1', 3, 4, NULL, 5),
('board1', 3, 5, 6, 6),
('board1', 3, 6, NULL, 3),
('board1', 3, 7, 1, 1),
('board1', 3, 8, 8, 8),

-- Row 4 (5行目)
('board1', 4, 0, NULL, 8),
('board1', 4, 1, NULL, 3),
('board1', 4, 2, 7, 7),
('board1', 4, 3, NULL, 1),
('board1', 4, 4, NULL, 2),
('board1', 4, 5, NULL, 9),
('board1', 4, 6, NULL, 6),
('board1', 4, 7, 4, 4),
('board1', 4, 8, NULL, 5),

-- Row 5 (6行目)
('board1', 5, 0, 5, 5),
('board1', 5, 1, NULL, 1),
('board1', 5, 2, NULL, 6),
('board1', 5, 3, NULL, 8),
('board1', 5, 4, NULL, 4),
('board1', 5, 5, NULL, 3),
('board1', 5, 6, 9, 9),
('board1', 5, 7, NULL, 2),
('board1', 5, 8, NULL, 7),

-- Row 6 (7行目)
('board1', 6, 0, 4, 4),
('board1', 6, 1, NULL, 7),
('board1', 6, 2, NULL, 5),
('board1', 6, 3, NULL, 3),
('board1', 6, 4, NULL, 6),
('board1', 6, 5, 1, 1),
('board1', 6, 6, NULL, 8),
('board1', 6, 7, NULL, 9),
('board1', 6, 8, NULL, 2),

-- Row 7 (8行目)
('board1', 7, 0, NULL, 3),
('board1', 7, 1, NULL, 6),
('board1', 7, 2, 8, 8),
('board1', 7, 3, NULL, 4),
('board1', 7, 4, NULL, 9),
('board1', 7, 5, 2, 2),
('board1', 7, 6, NULL, 5),
('board1', 7, 7, 7, 7),
('board1', 7, 8, NULL, 1),

-- Row 8 (9行目)
('board1', 8, 0, NULL, 1),
('board1', 8, 1, 2, 2),
('board1', 8, 2, NULL, 9),
('board1', 8, 3, 5, 5),
('board1', 8, 4, NULL, 8),
('board1', 8, 5, NULL, 7),
('board1', 8, 6, NULL, 4),
('board1', 8, 7, 6, 6),
('board1', 8, 8, 3, 3);