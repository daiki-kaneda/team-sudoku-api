INSERT INTO users (uid, name, email, created_at, is_active)
VALUES (
    '${admin.uid}',
    '${admin.name}',
    '${admin.email}',
    CURRENT_TIMESTAMP,
    TRUE
);

INSERT INTO user_role (user_id, role_id)
VALUES (
    '${admin.uid}',
    (SELECT id FROM roles WHERE role_name = 'ROLE_ADMIN')
);

INSERT INTO user_role (user_id, role_id)
VALUES (
    '${admin.uid}',
    (SELECT id FROM roles WHERE role_name = 'ROLE_USER')
);