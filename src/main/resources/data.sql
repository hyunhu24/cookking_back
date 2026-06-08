-- CameraColor
INSERT INTO camera_color (camera_color_id, camera_color_name) VALUES
(1, 'blue'),
(2, 'green'),
(3, 'pink'),
(4, 'purple'),
(5, 'red'),
(6, 'yellow');

-- CameraImage
INSERT INTO camera_image (camera_image_id, image_url, camera_color_id) VALUES
(1, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/cameraImage/1-blue.png', 1),
(2, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/cameraImage/1-yellow.png', 6),
(3, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/cameraImage/1-purple.png', 4),
(4, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/cameraImage/1-green.png', 2),
(5, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/cameraImage/1-red.png', 5),
(6, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/cameraImage/1-pink.png', 3),

(7, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/cameraImage/2-blue.png', 1),
(8, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/cameraImage/2-yellow.png', 6),
(9, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/cameraImage/2-purple.png', 4),
(10, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/cameraImage/2-green.png', 2),
(11, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/cameraImage/2-red.png', 5),
(12, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/cameraImage/2-pink.png', 3),

(13, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/cameraImage/3-blue.png', 1),
(14, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/cameraImage/3-yellow.png', 6),
(15, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/cameraImage/3-purple.png', 4),
(16, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/cameraImage/3-green.png', 2),
(17, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/cameraImage/3-red.png', 5),
(18, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/cameraImage/3-pink.png', 3),

(19, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/cameraImage/4-blue.png', 1),
(20, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/cameraImage/4-yellow.png', 6),
(21, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/cameraImage/4-purple.png', 4),
(22, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/cameraImage/4-green.png', 2),
(23, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/cameraImage/4-red.png', 5),
(24, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/cameraImage/4-pink.png', 3),

(25, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/cameraImage/5-blue.png', 1),
(26, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/cameraImage/5-yellow.png', 6),
(27, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/cameraImage/5-purple.png', 4),
(28, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/cameraImage/5-green.png', 2),
(29, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/cameraImage/5-red.png', 5),
(30, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/cameraImage/5-pink.png', 3);


-- 1. 도전과제 카테고리
INSERT INTO challenge_category (name, description, category, max_level, created_at, modified_at) VALUES
('한식', '한식 요리 관련 도전과제', '한식', 3, NOW(), NOW()),
('일식', '일식 요리 관련 도전과제', '일식', 3, NOW(), NOW()),
('중식', '중식 요리 관련 도전과제', '중식', 3, NOW(), NOW()),
('양식', '양식 요리 관련 도전과제', '양식', 3, NOW(), NOW()),
('좋아요', '좋아요 관련 도전과제', '좋아요', 3, NOW(), NOW()),
('북마크', '북마크 관련 도전과제', '북마크', 3, NOW(), NOW()),
('밥풀', '밥풀 관련 도전과제', '밥풀', 5, NOW(), NOW()),
('도감', '도감 관련 도전과제', '도감', 3, NOW(), NOW()),
('초보자', '회원가입 도전과제', '초보자', 0, NOW(), NOW());

-- 2. 도전과제 레벨 (각 카테고리당 레벨별 목표 수치)
INSERT INTO challenge_level (level, goal_count, challenge_category_id, description, image_path) VALUES
(1, 10, 1, '한식 레시피 10개 업로드', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/challenge/korean/1.png'),
(2, 20, 1, '한식 레시피 20개 업로드', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/challenge/korean/2.png'),
(3, 30, 1, '한식 레시피 30개 업로드', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/challenge/korean/3.png'),
(1, 10, 2, '일식 레시피 10개 업로드', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/challenge/japanese/1.png'),
(2, 20, 2, '일식 레시피 20개 업로드', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/challenge/japanese/2.png'),
(3, 30, 2, '일식 레시피 30개 업로드', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/challenge/japanese/3.png'),
(1, 10, 3, '중식 레시피 10개 업로드', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/challenge/chinese/1.png'),
(2, 20, 3, '중식 레시피 20개 업로드', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/challenge/chinese/2.png'),
(3, 30, 3, '중식 레시피 30개 업로드', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/challenge/chinese/3.png'),
(1, 10, 4, '양식 레시피 10개 업로드', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/challenge/western/1.png'),
(2, 20, 4, '양식 레시피 20개 업로드', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/challenge/western/2.png'),
(3, 30, 4, '양식 레시피 30개 업로드', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/challenge/western/3.png'),
(1, 10, 5, '내 레시피 좋아요 10회 달성', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/challenge/like/1.png'),
(2, 20, 5, '내 레시피 좋아요 20회 달성', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/challenge/like/2.png'),
(3, 30, 5, '내 레시피 좋아요 30회 달성', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/challenge/like/3.png'),
(1, 10, 6, '내 레시피 북마크 10회 달성', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/challenge/bookmark/1.png'),
(2, 20, 6, '내 레시피 북마크 20회 달성', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/challenge/bookmark/2.png'),
(3, 30, 6, '내 레시피 북마크 30회 달성', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/challenge/bookmark/3.png'),
(1, 10, 7, '10 밥풀 획득', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/rice/1.png'),
(2, 25, 7, '25 밥풀 획득', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/rice/2.png'),
(3, 50, 7, '50 밥풀 획득', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/rice/3.png'),
(4, 75, 7, '75 밥풀 획득', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/rice/4.png'),
(5, 100, 7, '100 밥풀 획득', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/rice/5.png'),
(1, 10, 8, '도감 사진 10개 등록', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/challenge/recipe/1.png'),
(2, 20, 8, '도감 사진 20개 등록', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/challenge/recipe/2.png'),
(3, 30, 8, '도감 사진 30개 등록', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/challenge/recipe/3.png'),
(0, 0, 9, '회원 가입시', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/challenge/basic/basic.png');

-- 3. 칭호 (도전과제별 레벨 달성 시 부여)
INSERT INTO title (name, level, image_path, type, challenge_category_id, created_at, modified_at) VALUES
('한식 초보', 1, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/challenge/korean/1.png', '한식', 1, NOW(), NOW()),
('한식 중수', 2, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/challenge/korean/2.png', '한식', 1, NOW(), NOW()),
('한식 고수', 3, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/challenge/korean/3.png', '한식', 1, NOW(), NOW()),
('일식 초보', 1, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/challenge/japanese/1.png', '일식', 2, NOW(), NOW()),
('일식 중수', 2, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/challenge/japanese/2.png', '일식', 2, NOW(), NOW()),
('일식 고수', 3, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/challenge/japanese/3.png', '일식', 2, NOW(), NOW()),
('중식 초보', 1, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/challenge/chinese/1.png', '중식', 3, NOW(), NOW()),
('중식 중수', 2, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/challenge/chinese/2.png', '중식', 3, NOW(), NOW()),
('중식 고수', 3, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/challenge/chinese/3.png', '중식', 3, NOW(), NOW()),
('양식 초보', 1, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/challenge/western/1.png', '양식', 4, NOW(), NOW()),
('양식 중수', 2, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/challenge/western/2.png', '양식', 4, NOW(), NOW()),
('양식 고수', 3, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/challenge/western/3.png', '양식', 4, NOW(), NOW()),
('인기쟁이', 1, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/challenge/like/1.png', '좋아요', 5, NOW(), NOW()),
('인기스타', 2, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/challenge/like/2.png', '좋아요', 5, NOW(), NOW()),
('인기왕', 3, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/challenge/like/3.png', '좋아요', 5, NOW(), NOW()),
('북마크', 1, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/challenge/bookmark/1.png', '북마크', 6, NOW(), NOW()),
('북마크', 2, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/challenge/bookmark/2.png', '북마크', 6, NOW(), NOW()),
('북마크', 3, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/challenge/bookmark/3.png', '북마크', 6, NOW(), NOW()),
('도감 초보', 1, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/challenge/recipe/1.png', '도감', 8, NOW(), NOW()),
('도감 중수', 2, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/challenge/recipe/2.png', '도감', 8, NOW(), NOW()),
('도감 고수', 3, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/challenge/recipe/3.png', '도감', 8, NOW(), NOW()),
('쌀 한톨', 1, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/rice/1.png', '밥풀', 7, NOW(), NOW()),
('한 숟가락', 2, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/rice/2.png', '밥풀', 7, NOW(), NOW()),
('한 그릇', 3, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/rice/3.png', '밥풀', 7, NOW(), NOW()),
('한 솥', 4, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/rice/4.png', '밥풀', 7, NOW(), NOW()),
('한 포대', 5, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/rice/5.png', '밥풀', 7, NOW(), NOW()),
('초보자', 1, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/challenge/basic/basic.png', '웰컴', 9, NOW(), NOW());

INSERT INTO payment_master (amount, rice_amount, image, name) VALUES
(1000, 10, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/rice/1.png', '한 톨'),
(2500, 25, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/rice/2.png', '한 숟가락'),
(5000, 50, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/rice/3.png', '한 공기'),
(7500, 75, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/rice/4.png', '한 밥솥'),
(10000, 100, 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/rice/5.png', '한 포대');

INSERT INTO member (login_id, email, nick_name, password, phone_number, profile, active_title_id, rice_point, member_status, created_at, modified_at, active_image_id) VALUES
('tjsk2222', 'tjsk2222@gmail.com', '택택', '{bcrypt}$2a$10$n1ZYr9wraXA.1sJ4YRoIH./kfrVUKtgwAwPhyshUXK3xc32.Jvd6.', '010-1111-2222', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/profile/m_default.png', 4, 100, 'MEMBER_ACTIVE',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
( 'user2', 'user2@example.com', '정희', '{bcrypt}$2a$10$n1ZYr9wraXA.1sJ4YRoIH./kfrVUKtgwAwPhyshUXK3xc32.Jvd6.', '052-115-7815', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/profile/m_default.png', 1, 100, 'MEMBER_ACTIVE', '2024-01-01 10:00:00', '2024-01-02 10:00:00', 1),
( 'user3', 'user3@example.com', '현우', '{bcrypt}$2a$10$n1ZYr9wraXA.1sJ4YRoIH./kfrVUKtgwAwPhyshUXK3xc32.Jvd6.', '052-232-0947', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/profile/m_default.png', 27, 100, 'MEMBER_ACTIVE', '2024-01-01 10:00:00', '2024-01-02 10:00:00', 1),
( 'user4', 'user4@example.com', '민재', '{bcrypt}$2a$10$n1ZYr9wraXA.1sJ4YRoIH./kfrVUKtgwAwPhyshUXK3xc32.Jvd6.', '063-515-9179', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/profile/m_default.png', 1, 50, 'MEMBER_ACTIVE', '2024-01-01 10:00:00', '2024-01-02 10:00:00', 1),
( 'user5', 'user5@example.com', '숙자', '{bcrypt}$2a$10$n1ZYr9wraXA.1sJ4YRoIH./kfrVUKtgwAwPhyshUXK3xc32.Jvd6.', '033-399-1615', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/profile/m_default.png', 27, 100, 'MEMBER_ACTIVE', '2024-01-01 10:00:00', '2024-01-02 10:00:00', 1),
( 'user6', 'user6@example.com', '정순', '{bcrypt}$2a$10$n1ZYr9wraXA.1sJ4YRoIH./kfrVUKtgwAwPhyshUXK3xc32.Jvd6.', '061-208-7091', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/profile/w_default.png', 7, 150, 'MEMBER_ACTIVE', '2024-01-01 10:00:00', '2024-01-02 10:00:00', 2),
( 'user7', 'user7@example.com', '영희', '{bcrypt}$2a$10$n1ZYr9wraXA.1sJ4YRoIH./kfrVUKtgwAwPhyshUXK3xc32.Jvd6.', '062-207-6984', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/profile/w_default.png', 3, 200, 'MEMBER_ACTIVE', '2024-01-01 10:00:00', '2024-01-02 10:00:00', 2),
( 'user8', 'user8@example.com', '순자', '{bcrypt}$2a$10$n1ZYr9wraXA.1sJ4YRoIH./kfrVUKtgwAwPhyshUXK3xc32.Jvd6.', '070-6109-3523', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/profile/w_default.png', 27, 225, 'MEMBER_ACTIVE', '2024-01-01 10:00:00', '2024-01-02 10:00:00', 2),
( 'user9', 'user9@example.com', '영수', '{bcrypt}$2a$10$n1ZYr9wraXA.1sJ4YRoIH./kfrVUKtgwAwPhyshUXK3xc32.Jvd6.', '031-754-7063', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/profile/w_default.png', 27, 120, 'MEMBER_ACTIVE', '2024-01-01 10:00:00', '2024-01-02 10:00:00', 2),
( 'user10', 'user10@example.com', '민지', '{bcrypt}$2a$10$n1ZYr9wraXA.1sJ4YRoIH./kfrVUKtgwAwPhyshUXK3xc32.Jvd6.', '051-217-6104', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/profile/w_default.png', 27, 50, 'MEMBER_ACTIVE', '2024-01-01 10:00:00', '2024-01-02 10:00:00',2);

-- 5. 회원별 도전과제 진행 상태
INSERT INTO member_challenge (member_id, challenge_category_id, current_level, current_count, created_at, modified_at) VALUES
(1, 1, 1, 0, NOW(), NOW()),
(1, 2, 1, 0, NOW(), NOW()),
(1, 3, 1, 1, NOW(), NOW()),
(1, 4, 1, 0, NOW(), NOW()),
(1, 5, 1, 0, NOW(), NOW()),
(1, 6, 1, 0, NOW(), NOW()),
(1, 7, 1, 0, NOW(), NOW()),
(1, 8, 1, 0, NOW(), NOW()),
(1, 9, 0, 0, NOW(), NOW()),

(2, 1, 1, 1, NOW(), NOW()),
(2, 2, 1, 1, NOW(), NOW()),
(2, 3, 1, 1, NOW(), NOW()),
(2, 4, 1, 1, NOW(), NOW()),
(2, 5, 1, 1, NOW(), NOW()),
(2, 6, 1, 1, NOW(), NOW()),
(2, 7, 1, 1, NOW(), NOW()),
(2, 8, 1, 1, NOW(), NOW()),
(2, 9, 0, 1, NOW(), NOW()),

(3, 1, 1, 1, NOW(), NOW()),
(3, 2, 1, 1, NOW(), NOW()),
(3, 3, 1, 1, NOW(), NOW()),
(3, 4, 1, 1, NOW(), NOW()),
(3, 5, 1, 1, NOW(), NOW()),
(3, 6, 1, 1, NOW(), NOW()),
(3, 7, 1, 1, NOW(), NOW()),
(3, 8, 1, 1, NOW(), NOW()),
(3, 9, 0, 1, NOW(), NOW()),

(4, 1, 1, 1, NOW(), NOW()),
(4, 2, 1, 1, NOW(), NOW()),
(4, 3, 1, 1, NOW(), NOW()),
(4, 4, 1, 1, NOW(), NOW()),
(4, 5, 1, 1, NOW(), NOW()),
(4, 6, 1, 1, NOW(), NOW()),
(4, 7, 1, 1, NOW(), NOW()),
(4, 8, 1, 1, NOW(), NOW()),
(4, 9, 0, 1, NOW(), NOW()),

(5, 1, 1, 1, NOW(), NOW()),
(5, 2, 1, 1, NOW(), NOW()),
(5, 3, 1, 1, NOW(), NOW()),
(5, 4, 1, 1, NOW(), NOW()),
(5, 5, 1, 1, NOW(), NOW()),
(5, 6, 1, 1, NOW(), NOW()),
(5, 7, 1, 1, NOW(), NOW()),
(5, 8, 1, 1, NOW(), NOW()),
(5, 9, 0, 1, NOW(), NOW()),

(6, 1, 1, 1, NOW(), NOW()),
(6, 2, 1, 1, NOW(), NOW()),
(6, 3, 1, 1, NOW(), NOW()),
(6, 4, 1, 1, NOW(), NOW()),
(6, 5, 1, 1, NOW(), NOW()),
(6, 6, 1, 1, NOW(), NOW()),
(6, 7, 1, 1, NOW(), NOW()),
(6, 8, 1, 1, NOW(), NOW()),
(6, 9, 0, 1, NOW(), NOW()),

(7, 1, 1, 1, NOW(), NOW()),
(7, 2, 1, 1, NOW(), NOW()),
(7, 3, 1, 1, NOW(), NOW()),
(7, 4, 1, 1, NOW(), NOW()),
(7, 5, 1, 1, NOW(), NOW()),
(7, 6, 1, 1, NOW(), NOW()),
(7, 7, 1, 1, NOW(), NOW()),
(7, 8, 1, 1, NOW(), NOW()),
(7, 9, 0, 1, NOW(), NOW()),

(8, 1, 1, 1, NOW(), NOW()),
(8, 2, 1, 1, NOW(), NOW()),
(8, 3, 1, 1, NOW(), NOW()),
(8, 4, 1, 1, NOW(), NOW()),
(8, 5, 1, 1, NOW(), NOW()),
(8, 6, 1, 1, NOW(), NOW()),
(8, 7, 1, 1, NOW(), NOW()),
(8, 8, 1, 1, NOW(), NOW()),
(8, 9, 0, 1, NOW(), NOW()),

(9, 1, 1, 1, NOW(), NOW()),
(9, 2, 1, 1, NOW(), NOW()),
(9, 3, 1, 1, NOW(), NOW()),
(9, 4, 1, 1, NOW(), NOW()),
(9, 5, 1, 1, NOW(), NOW()),
(9, 6, 1, 1, NOW(), NOW()),
(9, 7, 1, 1, NOW(), NOW()),
(9, 8, 1, 1, NOW(), NOW()),
(9, 9, 0, 1, NOW(), NOW()),

(10, 1, 1, 1, NOW(), NOW()),
(10, 2, 1, 1, NOW(), NOW()),
(10, 3, 1, 1, NOW(), NOW()),
(10, 4, 1, 1, NOW(), NOW()),
(10, 5, 1, 1, NOW(), NOW()),
(10, 6, 1, 1, NOW(), NOW()),
(10, 7, 1, 1, NOW(), NOW()),
(10, 8, 1, 1, NOW(), NOW()),
(10, 9, 0, 1, NOW(), NOW());

-- 6. 회원별 칭호 보유 내역
INSERT INTO member_title (member_id, title_id, created_at, modified_at) VALUES
(1, 1, '2025-05-01 14:00:00', '2025-05-01 14:00:00'),
(1, 4, '2025-05-02 14:00:00', '2025-05-02 14:00:00'),
(2, 1, '2025-05-03 14:00:00', '2025-05-03 14:00:00'),
(2, 3, '2025-05-04 14:00:00', '2025-05-04 14:00:00'),
(2, 9, '2025-05-05 14:00:00', '2025-05-05 14:00:00'),
(3, 27, '2025-05-06 14:00:00', '2025-05-06 14:00:00'),
(4, 1, '2025-05-07 14:00:00', '2025-05-07 14:00:00'),
(5, 27, '2025-05-08 14:00:00', '2025-05-08 14:00:00'),
(6, 1, '2025-05-09 14:00:00', '2025-05-09 14:00:00'),
(6, 7, '2025-05-10 14:00:00', '2025-05-10 14:00:00'),
(6, 10, '2025-05-11 14:00:00', '2025-05-11 14:00:00'),
(6, 11, '2025-05-12 14:00:00', '2025-05-12 14:00:00'),
(7, 1, '2025-05-13 14:00:00', '2025-05-13 14:00:00'),
(7, 2, '2025-05-14 14:00:00', '2025-05-14 14:00:00'),
(7, 3, '2025-05-15 14:00:00', '2025-05-15 14:00:00'),
(8, 27, '2025-05-16 14:00:00', '2025-05-16 14:00:00'),
(9, 27, '2025-05-17 14:00:00', '2025-05-17 14:00:00'),
(10, 27, '2025-05-18 14:00:00', '2025-05-18 14:00:00');


INSERT INTO member_roles (member_member_id, roles) VALUES
(1, 'USER'),
(2, 'USER'),
(3, 'USER'),
(4, 'USER'),
(5, 'USER'),
(6, 'USER'),
(7, 'USER'),
(8, 'USER'),
(9, 'USER'),
(10, 'USER');

INSERT INTO collection_camera (created_at, modified_at, collection_status, custom_category_name, camera_image_id, member_id)
VALUES
(NOW(), NOW(), 'PUBLIC', '도감 예시', 1, 1),
(NOW(), NOW(), 'PUBLIC', '도감 예시', 1, 2),
(NOW(), NOW(), 'PUBLIC', '도감 예시', 1, 3),
(NOW(), NOW(), 'PUBLIC', '도감 예시', 1, 4),
(NOW(), NOW(), 'PUBLIC', '도감 예시', 1, 5),
(NOW(), NOW(), 'PUBLIC', '도감 예시', 1, 6),
(NOW(), NOW(), 'PUBLIC', '도감 예시', 1, 7),
(NOW(), NOW(), 'PUBLIC', '도감 예시', 1, 8),
(NOW(), NOW(), 'PUBLIC', '도감 예시', 1, 9),
(NOW(), NOW(), 'PUBLIC', '도감 예시', 1, 10);

INSERT INTO payment
(amount, rice_amount, refund_reason, payment_status, member_id, payment_key, order_id, requested_at, completed_at, created_at, modified_at)
VALUES
(1000, 10, '사유1', 'COMPLETED', 10, 'payment_key_1', 'order_id_1', '2025-04-18 03:14:15', '2025-04-18 03:14:15', '2024-01-01 10:00:00', '2024-01-02 10:00:00'),
(5000, 50, '사유2', 'COMPLETED', 7,  'payment_key_2', 'order_id_2', '2025-04-18 03:14:15', '2025-04-18 03:14:15', '2024-01-01 10:00:00', '2024-01-02 10:00:00'),
(2500, 25, '사유3', 'COMPLETED', 6,  'payment_key_3', 'order_id_3', '2025-04-18 03:14:15', '2025-04-18 03:14:15', '2024-01-01 10:00:00', '2024-01-02 10:00:00'),
(5000, 50, '사유4', 'COMPLETED', 3,  'payment_key_4', 'order_id_4', '2025-04-18 03:14:15', '2025-04-18 03:14:15', '2024-01-01 10:00:00', '2024-01-02 10:00:00'),
(7500, 75, '사유5', 'COMPLETED', 3,  'payment_key_5', 'order_id_5', '2025-04-18 03:14:15', '2025-04-18 03:14:15', '2024-01-01 10:00:00', '2024-01-02 10:00:00'),
(10000, 100, '사유6', 'COMPLETED', 9,  'payment_key_6', 'order_id_6', '2025-04-18 03:14:15', '2025-04-18 03:14:15', '2024-01-01 10:00:00', '2024-01-02 10:00:00'),
(10000, 100, '사유7', 'COMPLETED', 10, 'payment_key_7', 'order_id_7', '2025-04-18 03:14:15', '2025-04-18 03:14:15', '2024-01-01 10:00:00', '2024-01-02 10:00:00'),
(1000, 10, '사유8', 'COMPLETED', 3,  'payment_key_8', 'order_id_8', '2025-04-18 03:14:15', '2025-04-18 03:14:15', '2024-01-01 10:00:00', '2024-01-02 10:00:00'),
(5000, 50, '사유9', 'COMPLETED', 7,  'payment_key_9', 'order_id_9', '2025-04-18 03:14:15', '2025-04-18 03:14:15', '2024-01-01 10:00:00', '2024-01-02 10:00:00'),
(2500, 25, '사유10', 'COMPLETED', 6, 'payment_key_10', 'order_id_10', '2025-04-18 03:14:15', '2025-04-18 03:14:15', '2024-01-01 10:00:00', '2024-01-02 10:00:00');

INSERT INTO menu_category (menu_category_name, created_at, modified_at) VALUES
( '한식', '2024-01-01 10:00:00', '2024-01-02 10:00:00'),
( '일식', '2024-01-01 10:00:00', '2024-01-02 10:00:00'),
( '중식', '2024-01-01 10:00:00', '2024-01-02 10:00:00'),
( '양식', '2024-01-01 10:00:00', '2024-01-02 10:00:00'),
( '기타', '2024-01-01 10:00:00', '2024-01-02 10:00:00');

INSERT INTO menu (menu_name, menu_category_id, created_at, modified_at) VALUES
( '김치찌개', 1, '2025-05-01 14:00:00', '2025-05-02 10:00:00'),
( '김밥', 1, '2025-05-02 14:00:00', '2025-05-03 10:00:00'),
( '연어 초밥', 2, '2025-05-03 14:00:00', '2025-05-04 10:00:00'),
( '가츠동', 2, '2025-05-04 14:00:00', '2025-05-05 10:00:00'),
( '짜장면', 3, '2025-05-05 14:00:00', '2025-05-06 10:00:00'),
( '짬뽕', 3, '2025-05-06 14:00:00', '2025-05-07 10:00:00'),
( '마라탕', 3, '2025-05-07 14:00:00', '2025-05-08 10:00:00'),
( '소고기 스테이크', 4, '2025-05-08 14:00:00', '2025-05-09 10:00:00'),
( '봉골레 파스타', 4, '2025-05-10 14:00:00', '2025-05-11 10:00:00');

INSERT INTO sub_menu_category (sub_menu_category_name, menu_category_id, created_at, modified_at) VALUES
( '빵', 5, '2025-05-11 14:00:00', '2025-05-12 10:00:00');

INSERT INTO menu (menu_name, menu_category_id, created_at, modified_at, sub_menu_category_id) VALUES ( '잠봉뵈르', 5, NOW(), NOW(), 1);


INSERT INTO recipe_board (title, image, recipe_status, recipe_board_status, member_id, menu_id, created_at, modified_at) VALUES
( '어머니의 손맛이 느껴지는 김치찌개','https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/menu/1.png',  'RECIPE_PRIVATE', 'RECIPE_BOARD_POST', 5, 1, '2025-05-01 14:00:00', '2025-05-02 10:00:00'),
( '야채 김밥','https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/menu/2.png',  'RECIPE_PUBLIC', 'RECIPE_BOARD_POST', 10, 2, '2025-05-02 14:00:00', '2025-05-03 10:00:00'),
( '바다의 향이 느껴지는 연어 초밥','https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/menu/3.png',  'RECIPE_PRIVATE', 'RECIPE_BOARD_POST', 5, 3, '2025-05-03 14:00:00', '2025-05-04 10:00:00'),
( '바삭함이 느껴지는 가츠동','https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/menu/4.png',  'RECIPE_PUBLIC', 'RECIPE_BOARD_POST', 2, 4, '2025-05-04 14:00:00', '2024-05-05 10:00:00'),
( '한국인이 좋아하는 짜장면','https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/menu/5.png',  'RECIPE_PRIVATE', 'RECIPE_BOARD_POST', 8, 5, '2024-05-05 14:00:00', '2024-05-06 10:00:00'),
( '맵찔이도 먹을 수 있는 짬뽕','https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/menu/6.png',  'RECIPE_PUBLIC', 'RECIPE_BOARD_POST', 1, 6, '2024-05-06 14:00:00', '2024-05-07 10:00:00'),
( '마라마라 마라탕','https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/menu/7.png',  'RECIPE_PRIVATE', 'RECIPE_BOARD_POST', 7, 7, '2024-05-07 14:00:00', '2024-05-08 10:00:00'),
( '본토의 맛이 느껴지는 소고기 스테이크','https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/menu/8.png',  'RECIPE_PUBLIC', 'RECIPE_BOARD_POST', 10, 8, '2024-05-08 14:00:00', '2024-05-09 10:00:00'),
( '이탈리아에서 전수받은 봉골레 파스타','https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/menu/9.png',  'RECIPE_PRIVATE', 'RECIPE_BOARD_POST', 9, 9, '2024-05-09 14:00:00', '2024-05-10 10:00:00'),
( '미슐랭 원스타의 비법이 녹아있는 잠봉뵈르','https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/menu/10.png',  'RECIPE_PUBLIC', 'RECIPE_BOARD_POST', 8, 10, '2024-05-10 14:00:00', '2024-05-11 10:00:00');

INSERT INTO bookmark (member_id, recipe_board_id, created_at, modified_at) VALUES
( 5, 1, '2025-05-01 14:00:00', '2025-05-02 10:00:00'),
( 9, 1, '2025-05-02 14:00:00', '2025-05-03 10:00:00'),
( 2, 7, '2025-05-03 14:00:00', '2025-05-04 10:00:00'),
( 1, 10, '2025-05-04 14:00:00', '2025-05-05 10:00:00'),
( 8, 6, '2025-05-05 14:00:00', '2025-05-06 10:00:00'),
( 4, 6, '2025-05-06 14:00:00', '2025-05-07 10:00:00'),
( 2, 4, '2025-05-07 14:00:00', '2025-05-08 10:00:00'),
( 10, 4, '2025-05-08 14:00:00', '2025-05-09 10:00:00'),
( 4, 3, '2025-05-09 14:00:00', '2025-05-10 10:00:00'),
( 9, 8, '2025-05-10 14:00:00', '2025-05-11 10:00:00');

INSERT INTO ingredient (ingredient_name, image, sub_category, dtype) VALUES
('갈비', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/1.png', '육류', 'MAIN'),
('갈치', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/2.png', '어류', 'MAIN'),
('닭똥집', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/3.png', '육류', 'MAIN'),
('닭발', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/4.png', '육류', 'MAIN'),
('망고', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/5.png', '과일', 'MAIN'),
('우둔살', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/6.png', '육류', 'MAIN'),
('삼겹살', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/7.png', '육류', 'MAIN'),
('샤인머스켓', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/8.png', '과일', 'MAIN'),
('차돌박이', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/9.png', '육류', 'MAIN'),
('가지', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/10.png', '채소', 'MAIN'),
('갈비살', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/11.png', '육류', 'MAIN'),
('감자', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/12.png', '채소', 'MAIN'),
('고구마', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/13.png', '채소', 'MAIN'),
('고등어', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/14.png', '어류', 'MAIN'),
('고추', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/15.png', '채소', 'MAIN'),
('광어', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/16.png', '어류', 'MAIN'),
('근대', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/17.png', '채소', 'MAIN'),
('깻잎', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/18.png', '채소', 'MAIN'),
('꽃게', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/19.png', '갑각류', 'MAIN'),
('날개', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/20.png', '육류', 'MAIN'),
('닭가슴살', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/21.png', '육류', 'MAIN'),
('닭다리살', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/22.png', '육류', 'MAIN'),
('당근', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/23.png', '채소', 'MAIN'),
('대게', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/24.png', '갑각류', 'MAIN'),
('도미', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/25.png', '어류', 'MAIN'),
('뒷다리살', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/26.png', '육류', 'MAIN'),
('등심', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/27.png', '육류', 'MAIN'),
('랍스터', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/28.png', '갑각류', 'MAIN'),
('마늘', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/29.png', '채소', 'MAIN'),
('목살', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/30.png', '육류', 'MAIN'),
('무', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/31.png', '채소', 'MAIN'),
('브로콜리', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/32.png', '채소', 'MAIN'),
('비트', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/33.png', '채소', 'MAIN'),
('사태', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/34.png', '육류', 'MAIN'),
('삼치', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/35.png', '어류', 'MAIN'),
('상추', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/36.png', '채소', 'MAIN'),
('새우', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/37.png', '갑각류', 'MAIN'),
('샐러리', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/38.png', '채소', 'MAIN'),
('생강', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/39.png', '채소', 'MAIN'),
('샬롯', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/40.png', '채소', 'MAIN'),
('시금치', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/41.png', '채소', 'MAIN'),
('아스파라거스', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/42.png', '채소', 'MAIN'),
('안심', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/43.png', '육류', 'MAIN'),
('앞다리살', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/44.png', '육류', 'MAIN'),
('애호박', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/45.png', '채소', 'MAIN'),
('양배추', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/46.png', '채소', 'MAIN'),
('양지', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/47.png', '육류', 'MAIN'),
('양파', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/48.png', '채소', 'MAIN'),
('연근', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/49.png', '채소', 'MAIN'),
('연어', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/50.png', '어류', 'MAIN'),
('오이', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/51.png', '채소', 'MAIN'),
('우럭', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/52.png', '어류', 'MAIN'),
('우엉', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/53.png', '채소', 'MAIN'),
('전어', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/54.png', '어류', 'MAIN'),
('조기', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/55.png', '어류', 'MAIN'),
('참치', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/56.png', '어류', 'MAIN'),
('청경채', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/57.png', '채소', 'MAIN'),
('치커리', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/58.png', '채소', 'MAIN'),
('케일', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/59.png', '채소', 'MAIN'),
('콜리플라워', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/60.png', '채소', 'MAIN'),
('토마토', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/61.png', '채소', 'MAIN'),
('파', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/62.png', '채소', 'MAIN'),
('파프리카', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/63.png', '채소', 'MAIN'),
('피망', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/64.png', '채소', 'MAIN'),
('호박', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/main/65.png', '채소', 'MAIN'),
('고추장', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/seasoning/1.png', null, 'SEASONING'),
('버터', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/seasoning/2.png', null, 'SEASONING'),
('후추', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/ingredient/seasoning/3.png',null, 'SEASONING');

-- 어머니의 손맛이 느껴지는 김치찌개 (menu_id: 1)
INSERT INTO menu_ingredient (menu_id, ingredient_id, created_at, modified_at) VALUES
(1, 31, NOW(), NOW()), -- 무
(1, 48, NOW(), NOW()), -- 양파
(1, 62, NOW(), NOW()), -- 파
(1, 21, NOW(), NOW()), -- 닭가슴살 (돼지고기 대체 불가 시)
(1, 15, NOW(), NOW()), -- 고추
(1, 66, NOW(), NOW()); -- 고추장

-- 야채 김밥 (menu_id: 2)
INSERT INTO menu_ingredient (menu_id, ingredient_id, created_at, modified_at) VALUES
(2, 23, NOW(), NOW()), -- 당근
(2, 41, NOW(), NOW()), -- 시금치
(2, 48, NOW(), NOW()), -- 양파
(2, 51, NOW(), NOW()), -- 오이
(2, 63, NOW(), NOW()); -- 파프리카

-- 바다의 향이 느껴지는 연어 초밥 (menu_id: 3)
INSERT INTO menu_ingredient (menu_id, ingredient_id, created_at, modified_at) VALUES
(3, 50, NOW(), NOW()), -- 연어
(3, 51, NOW(), NOW()), -- 오이
(3, 61, NOW(), NOW()); -- 토마토(가니쉬)

-- 바삭함이 느껴지는 가츠동 (menu_id: 4)
INSERT INTO menu_ingredient (menu_id, ingredient_id, created_at, modified_at) VALUES
(4, 26, NOW(), NOW()), -- 뒷다리살
(4, 27, NOW(), NOW()), -- 등심
(4, 48, NOW(), NOW()), -- 양파
(4, 39, NOW(), NOW()); -- 생강

-- 한국인이 좋아하는 짜장면 (menu_id: 5)
INSERT INTO menu_ingredient (menu_id, ingredient_id, created_at, modified_at) VALUES
(5, 48, NOW(), NOW()), -- 양파
(5, 23, NOW(), NOW()), -- 당근
(5, 62, NOW(), NOW()), -- 파
(5, 21, NOW(), NOW()); -- 닭가슴살 (대체용)

-- 맵찔이도 먹을 수 있는 짬뽕 (menu_id: 6)
INSERT INTO menu_ingredient (menu_id, ingredient_id, created_at, modified_at) VALUES
(6, 37, NOW(), NOW()), -- 새우
(6, 48, NOW(), NOW()), -- 양파
(6, 62, NOW(), NOW()), -- 파
(6, 23, NOW(), NOW()); -- 당근

-- 마라마라 마라탕 (menu_id: 7)
INSERT INTO menu_ingredient (menu_id, ingredient_id, created_at, modified_at) VALUES
(7, 37, NOW(), NOW()), -- 새우
(7, 22, NOW(), NOW()), -- 닭다리살
(7, 57, NOW(), NOW()), -- 청경채
(7, 41, NOW(), NOW()); -- 시금치

-- 본토의 맛이 느껴지는 소고기 스테이크 (menu_id: 8)
INSERT INTO menu_ingredient (menu_id, ingredient_id, created_at, modified_at) VALUES
(8, 43, NOW(), NOW()), -- 안심
(8, 27, NOW(), NOW()), -- 등심
(8, 38, NOW(), NOW()), -- 샐러리
(8, 32, NOW(), NOW()); -- 브로콜리

-- 이탈리아에서 전수받은 봉골레 파스타 (menu_id: 9)
INSERT INTO menu_ingredient (menu_id, ingredient_id, created_at, modified_at) VALUES
(9, 19, NOW(), NOW()), -- 꽃게 (조개 대체)
(9, 48, NOW(), NOW()), -- 양파
(9, 29, NOW(), NOW()), -- 마늘
(9, 61, NOW(), NOW()); -- 토마토

-- 미슐랭 원스타의 비법이 녹아있는 잠봉뵈르 (menu_id: 10)
INSERT INTO menu_ingredient (menu_id, ingredient_id, created_at, modified_at) VALUES
(10, 26, NOW(), NOW()), -- 뒷다리살(햄 대체)
(10, 48, NOW(), NOW()), -- 양파
(10, 61, NOW(), NOW()), -- 토마토
(10, 2, NOW(), NOW()); -- 갈치(비유사하지만 보충)

-- 어머니의 손맛이 느껴지는 김치찌개 (recipe_board_id: 1)
INSERT INTO recipe_board_ingredient (recipe_board_id, ingredient_id, created_at, modified_at) VALUES
(1, 31, NOW(), NOW()), -- 무
(1, 48, NOW(), NOW()), -- 양파
(1, 62, NOW(), NOW()), -- 파
(1, 21, NOW(), NOW()), -- 닭가슴살
(1, 15, NOW(), NOW()), -- 고추
(1, 66, NOW(), NOW()); -- 고추장

-- 야채 김밥 (recipe_board_id: 2)
INSERT INTO recipe_board_ingredient (recipe_board_id, ingredient_id, created_at, modified_at) VALUES
(2, 23, NOW(), NOW()), -- 당근
(2, 41, NOW(), NOW()), -- 시금치
(2, 48, NOW(), NOW()), -- 양파
(2, 51, NOW(), NOW()), -- 오이
(2, 63, NOW(), NOW()); -- 파프리카

-- 바다의 향이 느껴지는 연어 초밥 (recipe_board_id: 3)
INSERT INTO recipe_board_ingredient (recipe_board_id, ingredient_id, created_at, modified_at) VALUES
(3, 50, NOW(), NOW()), -- 연어
(3, 51, NOW(), NOW()), -- 오이
(3, 61, NOW(), NOW()); -- 토마토

-- 바삭함이 느껴지는 가츠동 (recipe_board_id: 4)
INSERT INTO recipe_board_ingredient (recipe_board_id, ingredient_id, created_at, modified_at) VALUES
(4, 26, NOW(), NOW()), -- 뒷다리살
(4, 27, NOW(), NOW()), -- 등심
(4, 48, NOW(), NOW()), -- 양파
(4, 39, NOW(), NOW()); -- 생강

-- 한국인이 좋아하는 짜장면 (recipe_board_id: 5)
INSERT INTO recipe_board_ingredient (recipe_board_id, ingredient_id, created_at, modified_at) VALUES
(5, 48, NOW(), NOW()), -- 양파
(5, 23, NOW(), NOW()), -- 당근
(5, 62, NOW(), NOW()), -- 파
(5, 21, NOW(), NOW()); -- 닭가슴살

-- 맵찔이도 먹을 수 있는 짬뽕 (recipe_board_id: 6)
INSERT INTO recipe_board_ingredient (recipe_board_id, ingredient_id, created_at, modified_at) VALUES
(6, 37, NOW(), NOW()), -- 새우
(6, 48, NOW(), NOW()), -- 양파
(6, 62, NOW(), NOW()), -- 파
(6, 23, NOW(), NOW()); -- 당근

-- 마라마라 마라탕 (recipe_board_id: 7)
INSERT INTO recipe_board_ingredient (recipe_board_id, ingredient_id, created_at, modified_at) VALUES
(7, 37, NOW(), NOW()), -- 새우
(7, 22, NOW(), NOW()), -- 닭다리살
(7, 57, NOW(), NOW()), -- 청경채
(7, 41, NOW(), NOW()); -- 시금치

-- 본토의 맛이 느껴지는 소고기 스테이크 (recipe_board_id: 8)
INSERT INTO recipe_board_ingredient (recipe_board_id, ingredient_id, created_at, modified_at) VALUES
(8, 43, NOW(), NOW()), -- 안심
(8, 27, NOW(), NOW()), -- 등심
(8, 38, NOW(), NOW()), -- 샐러리
(8, 32, NOW(), NOW()); -- 브로콜리

-- 이탈리아에서 전수받은 봉골레 파스타 (recipe_board_id: 9)
INSERT INTO recipe_board_ingredient (recipe_board_id, ingredient_id, created_at, modified_at) VALUES
(9, 19, NOW(), NOW()), -- 꽃게
(9, 48, NOW(), NOW()), -- 양파
(9, 29, NOW(), NOW()), -- 마늘
(9, 61, NOW(), NOW()); -- 토마토

-- 미슐랭 원스타의 비법이 녹아있는 잠봉뵈르 (recipe_board_id: 10)
INSERT INTO recipe_board_ingredient (recipe_board_id, ingredient_id, created_at, modified_at) VALUES
(10, 26, NOW(), NOW()), -- 뒷다리살
(10, 48, NOW(), NOW()), -- 양파
(10, 61, NOW(), NOW()), -- 토마토
(10, 2, NOW(), NOW()); -- 갈치 (단백질 대체용)

INSERT INTO recipe_step (title, created_at, modified_at) VALUES
( '재료 씻기', '2024-01-01 10:00:00', '2024-01-02 10:00:00'),
( '재료 손질하기', '2024-01-01 10:00:00', '2024-01-02 10:00:00'),
( '양념 만들기', '2024-01-01 10:00:00', '2024-01-02 10:00:00'),
( '재료 밑간하기', '2024-01-01 10:00:00', '2024-01-02 10:00:00'),
( '재료 볶기', '2024-01-01 10:00:00', '2024-01-02 10:00:00'),
( '재료 굽기', '2024-01-01 10:00:00', '2024-01-02 10:00:00'),
( '국물 끓이기', '2024-01-01 10:00:00', '2024-01-02 10:00:00'),
( '찜 요리하기', '2024-01-01 10:00:00', '2024-01-02 10:00:00'),
( '튀기기', '2024-01-01 10:00:00', '2024-01-02 10:00:00'),
( '오븐에 굽기', '2024-01-01 10:00:00', '2024-01-02 10:00:00'),
( '소스 만들기', '2024-01-01 10:00:00', '2024-01-02 10:00:00'),
( '음식 플레이팅', '2024-01-01 10:00:00', '2024-01-02 10:00:00');

INSERT INTO recipe_board_step (step_order, recipe_board_id, recipe_step_id, created_at, modified_at) VALUES
                                                                                                         -- 1번: 어머니의 손맛이 느껴지는 김치찌개
(1, 1, 1, NOW(), NOW()), -- 재료 씻기
(2, 1, 2, NOW(), NOW()), -- 재료 손질하기
(3, 1, 3, NOW(), NOW()), -- 양념 만들기
(4, 1, 4, NOW(), NOW()), -- 재료 밑간하기
(5, 1, 7, NOW(), NOW()), -- 국물 끓이기
(6, 1, 11, NOW(), NOW()), -- 소스 만들기 (김치찌개 양념)
(7, 1, 12, NOW(), NOW()), -- 음식 플레이팅

-- 2번: 야채 김밥
(1, 2, 1, NOW(), NOW()), -- 재료 씻기
(2, 2, 2, NOW(), NOW()), -- 재료 손질하기
(3, 2, 5, NOW(), NOW()), -- 재료 볶기 (단무지, 시금치 등 준비)
(4, 2, 6, NOW(), NOW()), -- 재료 굽기 (지단 부치기)
(5, 2, 4, NOW(), NOW()), -- 재료 밑간하기 (소금 간 등)
(6, 2, 12, NOW(), NOW()), -- 음식 플레이팅 (김밥 말기)

-- 3번: 바다의 향이 느껴지는 연어 초밥
(1, 3, 1, NOW(), NOW()), -- 재료 씻기
(2, 3, 2, NOW(), NOW()), -- 재료 손질하기 (연어 슬라이스)
(3, 3, 11, NOW(), NOW()), -- 소스 만들기 (초밥용 간장, 와사비)
(4, 3, 12, NOW(), NOW()), -- 음식 플레이팅

-- 4번: 바삭함이 느껴지는 가츠동
(1, 4, 1, NOW(), NOW()), -- 재료 씻기
(2, 4, 2, NOW(), NOW()), -- 재료 손질하기 (돈까스용 고기)
(3, 4, 9, NOW(), NOW()), -- 튀기기 (돈까스 튀기기)
(4, 4, 11, NOW(), NOW()), -- 소스 만들기 (가츠동 소스)
(5, 4, 12, NOW(), NOW()), -- 음식 플레이팅

-- 5번: 한국인이 좋아하는 짜장면
(1, 5, 1, NOW(), NOW()), -- 재료 씻기
(2, 5, 2, NOW(), NOW()), -- 재료 손질하기 (양파, 고기)
(3, 5, 5, NOW(), NOW()), -- 재료 볶기 (춘장 볶기)
(4, 5, 7, NOW(), NOW()), -- 국물 끓이기 (춘장 소스)
(5, 5, 12, NOW(), NOW()), -- 음식 플레이팅

-- 6번: 맵찔이도 먹을 수 있는 짬뽕
(1, 6, 1, NOW(), NOW()), -- 재료 씻기
(2, 6, 2, NOW(), NOW()), -- 재료 손질하기 (해물, 야채)
(3, 6, 5, NOW(), NOW()), -- 재료 볶기 (고추기름에 볶기)
(4, 6, 7, NOW(), NOW()), -- 국물 끓이기 (짬뽕 국물)
(5, 6, 12, NOW(), NOW()), -- 음식 플레이팅

-- 7번: 마라마라 마라탕
(1, 7, 1, NOW(), NOW()), -- 재료 씻기
(2, 7, 2, NOW(), NOW()), -- 재료 손질하기 (각종 야채, 고기)
(3, 7, 5, NOW(), NOW()), -- 재료 볶기 (마라소스 기름내기)
(4, 7, 7, NOW(), NOW()), -- 국물 끓이기 (마라탕 베이스)
(5, 7, 12, NOW(), NOW()), -- 음식 플레이팅

-- 8번: 본토의 맛이 느껴지는 소고기 스테이크
(1, 8, 1, NOW(), NOW()), -- 재료 씻기
(2, 8, 2, NOW(), NOW()), -- 재료 손질하기 (스테이크 고기 준비)
(3, 8, 6, NOW(), NOW()), -- 재료 굽기 (스테이크 굽기)
(4, 8, 11, NOW(), NOW()), -- 소스 만들기 (스테이크 소스)
(5, 8, 12, NOW(), NOW()), -- 음식 플레이팅

-- 9번: 이탈리아에서 전수받은 봉골레 파스타
(1, 9, 1, NOW(), NOW()), -- 재료 씻기
(2, 9, 2, NOW(), NOW()), -- 재료 손질하기 (조개, 마늘 준비)
(3, 9, 5, NOW(), NOW()), -- 재료 볶기 (올리브오일에 볶기)
(4, 9, 7, NOW(), NOW()), -- 국물 끓이기 (봉골레 소스)
(5, 9, 12, NOW(), NOW()), -- 음식 플레이팅

-- 10번: 미슐랭 원스타의 비법이 녹아있는 잠봉뵈르
(1, 10, 1, NOW(), NOW()), -- 재료 씻기
(2, 10, 2, NOW(), NOW()), -- 재료 손질하기 (햄, 치즈 준비)
(3, 10, 10, NOW(), NOW()), -- 오븐에 굽기 (빵 굽기)
(4, 10, 11, NOW(), NOW()), -- 소스 만들기 (머스타드 소스)
(5, 10, 12, NOW(), NOW()); -- 음식 플레이팅

INSERT INTO recipe_step_detail (detail_order_number, description, image, recipe_board_step_id, created_at, modified_at) VALUES
(1, '김치와 돼지고기, 야채를 깨끗이 씻어 준비합니다.', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/recipeStepDetail/RecipeStepSampleImage.png', 1, NOW(), NOW()),
(1, '씻은 김치와 고기를 한입 크기로 손질합니다.', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/recipeStepDetail/RecipeStepSampleImage.png', 2, NOW(), NOW()),
(1, '고춧가루, 다진 마늘, 설탕, 간장으로 양념장을 만듭니다.', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/recipeStepDetail/RecipeStepSampleImage.png', 3, NOW(), NOW()),
(1, '손질한 재료에 양념을 넣어 밑간을 합니다.', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/recipeStepDetail/RecipeStepSampleImage.png', 4, NOW(), NOW()),
(1, '물과 함께 재료를 넣고 끓입니다.', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/recipeStepDetail/RecipeStepSampleImage.png', 5, NOW(), NOW()),
(1, '된장과 고춧가루를 추가하여 깊은 맛을 더합니다.', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/recipeStepDetail/RecipeStepSampleImage.png', 6, NOW(), NOW()),
(1, '완성된 찌개를 그릇에 담아 플레이팅합니다.', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/recipeStepDetail/RecipeStepSampleImage.png', 7, NOW(), NOW()),

(1, '시금치, 단무지, 당근 등을 깨끗이 씻습니다.', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/recipeStepDetail/RecipeStepSampleImage.png', 8, NOW(), NOW()),
(1, '씻은 재료를 김밥용으로 손질합니다.', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/recipeStepDetail/RecipeStepSampleImage.png', 9, NOW(), NOW()),
(1, '당근을 볶고 시금치를 데쳐 물기를 짭니다.', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/recipeStepDetail/RecipeStepSampleImage.png', 10, NOW(), NOW()),
(1, '달걀을 풀어 지단을 부치고 식혀서 썰어줍니다.', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/recipeStepDetail/RecipeStepSampleImage.png', 11, NOW(), NOW()),
(1, '밥에 소금과 참기름으로 간을 맞춥니다.', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/recipeStepDetail/RecipeStepSampleImage.png', 12, NOW(), NOW()),
(1, '김에 밥과 재료를 올려 단단히 말아줍니다.', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/recipeStepDetail/RecipeStepSampleImage.png', 13, NOW(), NOW()),

(1, '연어와 오이를 깨끗이 씻습니다.', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/recipeStepDetail/RecipeStepSampleImage.png', 14, NOW(), NOW()),
(1, '연어를 얇고 넓게 썰어줍니다.', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/recipeStepDetail/RecipeStepSampleImage.png', 15, NOW(), NOW()),
(1, '초밥 간장과 와사비 소스를 준비합니다.', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/recipeStepDetail/RecipeStepSampleImage.png', 16, NOW(), NOW()),
(1, '밥 위에 연어를 얹어 초밥을 완성합니다.', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/recipeStepDetail/RecipeStepSampleImage.png', 17, NOW(), NOW()),

(1, '돈까스용 돼지고기와 양배추를 깨끗이 씻습니다.', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/recipeStepDetail/RecipeStepSampleImage.png', 18, NOW(), NOW()),
(1, '고기에 소금, 후추로 밑간을 합니다.', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/recipeStepDetail/RecipeStepSampleImage.png', 19, NOW(), NOW()),
(1, '튀김옷을 입힌 후 바삭하게 튀깁니다.', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/recipeStepDetail/RecipeStepSampleImage.png', 20, NOW(), NOW()),
(1, '양파와 가츠동 소스를 준비하여 끓입니다.', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/recipeStepDetail/RecipeStepSampleImage.png', 21, NOW(), NOW()),
(1, '튀긴 돈까스를 밥 위에 얹고 소스를 부어 마무리합니다.', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/recipeStepDetail/RecipeStepSampleImage.png', 22, NOW(), NOW()),

(1, '양파, 감자, 돼지고기를 깨끗이 씻습니다.', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/recipeStepDetail/RecipeStepSampleImage.png', 23, NOW(), NOW()),
(1, '모든 재료를 먹기 좋은 크기로 썰어줍니다.', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/recipeStepDetail/RecipeStepSampleImage.png', 24, NOW(), NOW()),
(1, '춘장을 기름에 볶아 고소한 향을 냅니다.', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/recipeStepDetail/RecipeStepSampleImage.png', 25, NOW(), NOW()),
(1, '볶은 춘장과 채소를 물과 함께 끓여 소스를 완성합니다.', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/recipeStepDetail/RecipeStepSampleImage.png', 26, NOW(), NOW()),
(1, '삶은 면에 소스를 부어 마무리합니다.', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/recipeStepDetail/RecipeStepSampleImage.png', 27, NOW(), NOW()),

(1, '오징어, 홍합, 야채를 깨끗이 씻습니다.', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/recipeStepDetail/RecipeStepSampleImage.png', 28, NOW(), NOW()),
(1, '야채는 채썰고 해물은 한입 크기로 준비합니다.', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/recipeStepDetail/RecipeStepSampleImage.png', 29, NOW(), NOW()),
(1, '고추기름에 해물과 야채를 볶아줍니다.', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/recipeStepDetail/RecipeStepSampleImage.png', 30, NOW(), NOW()),
(1, '물과 육수를 넣고 칼칼한 국물을 끓입니다.', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/recipeStepDetail/RecipeStepSampleImage.png', 31, NOW(), NOW()),
(1, '삶은 면에 국물을 부어 짬뽕을 완성합니다.', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/recipeStepDetail/RecipeStepSampleImage.png', 32, NOW(), NOW()),

(1, '숙주, 청경채, 소고기 등을 깨끗이 씻습니다.', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/recipeStepDetail/RecipeStepSampleImage.png', 33, NOW(), NOW()),
(1, '재료를 먹기 좋은 크기로 썰어줍니다.', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/recipeStepDetail/RecipeStepSampleImage.png', 34, NOW(), NOW()),
(1, '마라소스와 향신료를 기름에 볶습니다.', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/recipeStepDetail/RecipeStepSampleImage.png', 35, NOW(), NOW()),
(1, '육수와 함께 재료를 끓여 깊은 맛을 냅니다.', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/recipeStepDetail/RecipeStepSampleImage.png', 36, NOW(), NOW()),
(1, '건더기와 국물을 그릇에 담아 완성합니다.', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/recipeStepDetail/RecipeStepSampleImage.png', 37, NOW(), NOW()),

(1, '스테이크용 고기와 허브를 깨끗이 씻습니다.', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/recipeStepDetail/RecipeStepSampleImage.png', 38, NOW(), NOW()),
(1, '고기에 소금과 후추로 밑간을 합니다.', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/recipeStepDetail/RecipeStepSampleImage.png', 39, NOW(), NOW()),
(1, '센 불에 겉면을 바삭하게 구워줍니다.', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/recipeStepDetail/RecipeStepSampleImage.png', 40, NOW(), NOW()),
(1, '버터와 허브를 이용해 풍미를 더한 소스를 만듭니다.', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/recipeStepDetail/RecipeStepSampleImage.png', 41, NOW(), NOW()),
(1, '스테이크와 소스를 플레이팅합니다.', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/recipeStepDetail/RecipeStepSampleImage.png', 42, NOW(), NOW()),

(1, '조개와 마늘을 깨끗이 씻습니다.', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/recipeStepDetail/RecipeStepSampleImage.png', 43, NOW(), NOW()),
(1, '조개 껍질을 닦고 마늘을 편 썰어 준비합니다.', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/recipeStepDetail/RecipeStepSampleImage.png', 44, NOW(), NOW()),
(1, '올리브유에 마늘을 볶고 조개를 넣습니다.', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/recipeStepDetail/RecipeStepSampleImage.png', 45, NOW(), NOW()),
(1, '화이트와인과 육수를 넣고 끓여 조개의 입을 엽니다.', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/recipeStepDetail/RecipeStepSampleImage.png', 46, NOW(), NOW()),
(1, '파스타면과 소스를 섞어 플레이팅합니다.', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/recipeStepDetail/RecipeStepSampleImage.png', 47, NOW(), NOW()),

(1, '바게트와 햄, 치즈를 깨끗이 준비합니다.', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/recipeStepDetail/RecipeStepSampleImage.png', 48, NOW(), NOW()),
(1, '햄과 치즈를 슬라이스합니다.', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/recipeStepDetail/RecipeStepSampleImage.png', 49, NOW(), NOW()),
(1, '바게트를 오븐에 살짝 구워 바삭하게 만듭니다.', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/recipeStepDetail/RecipeStepSampleImage.png', 50, NOW(), NOW()),
(1, '머스타드 소스를 바르고 햄, 치즈를 올립니다.', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/recipeStepDetail/RecipeStepSampleImage.png', 51, NOW(), NOW()),
(1, '완성된 잠봉뵈르를 먹기 좋게 잘라 플레이팅합니다.', 'https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/recipeStepDetail/RecipeStepSampleImage.png', 52, NOW(), NOW());


-- ProfileImage 더미 데이터
INSERT INTO profile_image (image_path, price) VALUES
('https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/profile/m_default.png', 0),
('https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/profile/w_default.png', 0),
('https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/profile/1.png', 10),
('https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/profile/2.png', 10),
('https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/profile/3.png', 15),
('https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/profile/4.png', 15),
('https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/profile/5.png', 20),
('https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/profile/6.png', 20),
('https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/profile/7.png', 25),
('https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/profile/8.png', 25),
('https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/profile/9.png', 30),
('https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/profile/10.png', 30),
('https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/profile/11.png', 35),
('https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/profile/12.png', 35),
('https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/profile/13.png', 40),
('https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/profile/14.png', 40),
('https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/profile/15.png', 45),
('https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/profile/16.png', 45),
('https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/profile/17.png', 50),
('https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/profile/18.png', 50),
('https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/profile/19.png', 55),
('https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/profile/20.png', 55),
('https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/profile/21.png', 60),
('https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/profile/22.png', 60),
('https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/profile/23.png', 65),
('https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/profile/24.png', 65),
('https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/profile/25.png', 70),
('https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/profile/26.png', 70),
('https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/profile/27.png', 75),
('https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/profile/28.png', 75),
('https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/profile/29.png', 80),
('https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/profile/30.png', 80),
('https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/profile/31.png', 85),
('https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/profile/32.png', 85),
('https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/profile/33.png', 90),
('https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/profile/34.png', 90),
('https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/profile/35.png', 95),
('https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/profile/36.png', 95),
('https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/profile/37.png', 100),
('https://aws-cookking-bucket.s3.ap-northeast-2.amazonaws.com/profile/38.png', 100);

-- MemberProfileImage 더미 데이터 (member_id는 이미 존재한다고 가정)
INSERT INTO member_profile_image (member_id, profile_image_id) VALUES (1, 1);
INSERT INTO member_profile_image (member_id, profile_image_id) VALUES (2, 1);
INSERT INTO member_profile_image (member_id, profile_image_id) VALUES (3, 1);
INSERT INTO member_profile_image (member_id, profile_image_id) VALUES (4, 1);
INSERT INTO member_profile_image (member_id, profile_image_id) VALUES (5, 1);
INSERT INTO member_profile_image (member_id, profile_image_id) VALUES (6, 1);
INSERT INTO member_profile_image (member_id, profile_image_id) VALUES (7, 1);
INSERT INTO member_profile_image (member_id, profile_image_id) VALUES (8, 1);
INSERT INTO member_profile_image (member_id, profile_image_id) VALUES (9, 1);
INSERT INTO member_profile_image (member_id, profile_image_id) VALUES (10, 1);
INSERT INTO member_profile_image (member_id, profile_image_id) VALUES (1, 2);
INSERT INTO member_profile_image (member_id, profile_image_id) VALUES (2, 2);
INSERT INTO member_profile_image (member_id, profile_image_id) VALUES (3, 2);
INSERT INTO member_profile_image (member_id, profile_image_id) VALUES (4, 2);
INSERT INTO member_profile_image (member_id, profile_image_id) VALUES (5, 2);
INSERT INTO member_profile_image (member_id, profile_image_id) VALUES (6, 2);
INSERT INTO member_profile_image (member_id, profile_image_id) VALUES (7, 2);
INSERT INTO member_profile_image (member_id, profile_image_id) VALUES (8, 2);
INSERT INTO member_profile_image (member_id, profile_image_id) VALUES (9, 2);
INSERT INTO member_profile_image (member_id, profile_image_id) VALUES (10, 2);

INSERT INTO likes (member_id, recipe_board_id, created_at, modified_at) VALUES
( 1, 1, '2025-05-01 14:00:00', '2025-05-02 10:00:00'),
( 1, 2, '2025-05-02 14:00:00', '2025-05-03 10:00:00'),
( 3, 3, '2025-05-03 14:00:00', '2025-05-04 10:00:00'),
( 4, 4, '2025-05-04 14:00:00', '2025-05-05 10:00:00'),
( 5, 5, '2025-05-05 14:00:00', '2025-05-06 10:00:00'),
( 6, 6, '2025-05-06 14:00:00', '2025-05-07 10:00:00'),
( 1, 7, '2025-05-07 14:00:00', '2025-05-08 10:00:00'),
( 8, 1, '2025-05-08 14:00:00', '2025-05-09 10:00:00'),
( 9, 1, '2025-05-09 14:00:00', '2025-05-10 10:00:00'),
( 10, 1, '2025-05-10 14:00:00', '2025-05-11 10:00:00');