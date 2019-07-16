/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : guet

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 08/07/2019 10:02:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `course_code` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '课程代码',
  `course_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '课程名称',
  `course_credit` float(2, 0) NOT NULL COMMENT '学分',
  `course_date` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '上课周次',
  `course_type` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '选课类型',
  PRIMARY KEY (`course_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('BG0000007X0', '大学英语4', 0, '1-14', '公共必修');
INSERT INTO `course` VALUES ('BG0000009X0', '大学英语2', 3, '1-14', '公共必修');
INSERT INTO `course` VALUES ('BG0000026X0', '思想道德修养与法律基础', 3, '1-8', '公共必修');
INSERT INTO `course` VALUES ('BG0000175X0', '毛泽东思想和中国特色社会主义理论体系概论', 5, '1-18', '公共必修');
INSERT INTO `course` VALUES ('BJ000001410', '高等数学A2', 5, '1-18', '基础必修');
INSERT INTO `course` VALUES ('BJ0400285X0', '程序设计与问题求解', 2, '1-8', '专业基础必修');
INSERT INTO `course` VALUES ('BS0400286X3', '程序设计与问题求解实验', 1, '5-9', '实践环节');
INSERT INTO `course` VALUES ('BS0400353X3', 'Java程序设计实验', 1, '14-18', '实践环节');
INSERT INTO `course` VALUES ('BT0400350X0', 'Java程序设计', 1, '10-15', '专业基础必修');

-- ----------------------------
-- Table structure for login_info
-- ----------------------------
DROP TABLE IF EXISTS `login_info`;
CREATE TABLE `login_info`  (
  `UID` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '学生或老师id',
  `password` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '登录密码',
  `regis_date` datetime(0) NULL DEFAULT NULL COMMENT '注册时间',
  `login_date` datetime(0) NULL DEFAULT NULL COMMENT '上次登录时间',
  `status` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`UID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of login_info
-- ----------------------------
INSERT INTO `login_info` VALUES ('1700420201', '123456', NULL, '2019-07-07 13:13:15', '学生');
INSERT INTO `login_info` VALUES ('1700420202', '123456', NULL, '2019-07-05 17:48:23', '学生');
INSERT INTO `login_info` VALUES ('1700420203', '699709', NULL, NULL, '学生');
INSERT INTO `login_info` VALUES ('1700420204', '155733', NULL, NULL, '学生');
INSERT INTO `login_info` VALUES ('1700420205', '756640', NULL, NULL, '学生');
INSERT INTO `login_info` VALUES ('1700420206', '812658', NULL, NULL, '学生');
INSERT INTO `login_info` VALUES ('1700420207', '468630', NULL, NULL, '学生');
INSERT INTO `login_info` VALUES ('423859', '123456', NULL, '2019-07-07 12:57:47', '教师');
INSERT INTO `login_info` VALUES ('59424856', '123456', NULL, NULL, '管理员');
INSERT INTO `login_info` VALUES ('872865', '474924', NULL, NULL, '教师');

-- ----------------------------
-- Table structure for stu_course
-- ----------------------------
DROP TABLE IF EXISTS `stu_course`;
CREATE TABLE `stu_course`  (
  `id` bigint(255) NOT NULL AUTO_INCREMENT,
  `course_id` int(7) NOT NULL COMMENT '选课编号',
  `student_id` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '学号',
  `grade` float(3, 1) NULL DEFAULT NULL COMMENT '成绩',
  `semester` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '学期',
  `unique_code` varchar(21) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '学号与课程号的组合确保选课唯一',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unique_code`(`unique_code`) USING BTREE COMMENT '学号与课号的唯一',
  INDEX `fk_course_id`(`course_id`) USING BTREE,
  INDEX `fk_student_id`(`student_id`) USING BTREE,
  CONSTRAINT `fk_course_id` FOREIGN KEY (`course_id`) REFERENCES `tea_course` (`course_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_student_id` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 91 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stu_course
-- ----------------------------
INSERT INTO `stu_course` VALUES (53, 1820355, '1700420201', 81.0, '182', '1700420201BG0000175X0');
INSERT INTO `stu_course` VALUES (54, 1820253, '1700420201', 28.0, '182', '1700420201BG0000026X0');
INSERT INTO `stu_course` VALUES (79, 1821603, '1700420201', 60.0, '182', '1700420201BT0400350X0');
INSERT INTO `stu_course` VALUES (83, 1821604, '1700420201', 89.0, '182', '1700420201BS0400353X3');
INSERT INTO `stu_course` VALUES (85, 1720018, '1700420201', 90.0, '172', '1700420201BJ000001410');
INSERT INTO `stu_course` VALUES (86, 1721564, '1700420201', 78.0, '172', '1700420201BJ0400285X0');
INSERT INTO `stu_course` VALUES (87, 1721565, '1700420201', 94.0, '172', '1700420201BS0400286X3');
INSERT INTO `stu_course` VALUES (88, 1720615, '1700420201', NULL, '172', '1700420201BG0000009X0');
INSERT INTO `stu_course` VALUES (90, 1820253, '1700420202', 77.0, '182', '1700420202BG0000026X0');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `student_id` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '学号',
  `student_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '姓名',
  `student_sex` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '性别',
  `student_birthday` date NULL DEFAULT NULL COMMENT '生日',
  `birthplace` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '籍贯',
  PRIMARY KEY (`student_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1700420201', '邓应卓', '女', NULL, '常德');
INSERT INTO `student` VALUES ('1700420202', '董惜巧', '女', NULL, 'as');
INSERT INTO `student` VALUES ('1700420204', '黄梦莲', '女', NULL, NULL);
INSERT INTO `student` VALUES ('1700420205', '纪哲', '女', NULL, NULL);
INSERT INTO `student` VALUES ('1700420206', '梁加丽', '女', NULL, NULL);
INSERT INTO `student` VALUES ('1700420207', '庞思雅', '女', NULL, NULL);

-- ----------------------------
-- Table structure for tea_course
-- ----------------------------
DROP TABLE IF EXISTS `tea_course`;
CREATE TABLE `tea_course`  (
  `course_id` int(7) NOT NULL AUTO_INCREMENT COMMENT '课程序号，前两个数字代表学年，第三个数字代表学期\r\n例如：182代表\r\n2019-2018下半学年',
  `course_code` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '课程代码',
  `teacher_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '工号',
  `classroom` varchar(7) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '教室',
  `course_time` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '上课时间：例如\r\n112/234表示 星期一第一二节、星期二第三四节\r\n15678表示 星期一第五六七八节',
  `capacity` int(3) NULL DEFAULT NULL COMMENT '容量',
  PRIMARY KEY (`course_id`) USING BTREE,
  INDEX `fk_course_code`(`course_code`) USING BTREE,
  INDEX `fk_teacher_id`(`teacher_id`) USING BTREE,
  CONSTRAINT `fk_course_code` FOREIGN KEY (`course_code`) REFERENCES `course` (`course_code`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_teacher_id` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`teacher_id`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1821606 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tea_course
-- ----------------------------
INSERT INTO `tea_course` VALUES (1720018, 'BJ000001410', '275845', '02203*', '/21-2/43-4', 40);
INSERT INTO `tea_course` VALUES (1720615, 'BG0000009X0', '743586', '11103*', '/23-4/41-2', 40);
INSERT INTO `tea_course` VALUES (1721564, 'BJ0400285X0', '872865', '14103*', '/15-6/33-4', 40);
INSERT INTO `tea_course` VALUES (1721565, 'BS0400286X3', '872865', '14410*', '/35-8', 40);
INSERT INTO `tea_course` VALUES (1820252, 'BG0000026X0', '423859', '17412*', '/37-8/57-8', 40);
INSERT INTO `tea_course` VALUES (1820253, 'BG0000026X0', '423859', '17412*', '/17-8/47-8', 40);
INSERT INTO `tea_course` VALUES (1820355, 'BG0000175X0', '234849', '17512*', '/31-2/51-2', 40);
INSERT INTO `tea_course` VALUES (1820394, 'BG0000007X0', '743586', '11A211*', '/21-2/41-2', 40);
INSERT INTO `tea_course` VALUES (1821603, 'BT0400350X0', '846023', '14103*', '/11-2/33-4', 40);
INSERT INTO `tea_course` VALUES (1821604, 'BS0400353X3', '846023', '14410*', '/25-8', 1);
INSERT INTO `tea_course` VALUES (1821605, 'BS0400353X3', '846023', '14410*', '/45-8', 40);

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `teacher_id` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '工号',
  `teacher_name` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '姓名',
  `teacher_sex` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '性别',
  `teacher_birthday` date NULL DEFAULT NULL COMMENT '生日',
  `teacher_title` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '职称',
  `birthplace` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '籍贯',
  PRIMARY KEY (`teacher_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('234849', '陈元明', '男', NULL, '教师', NULL);
INSERT INTO `teacher` VALUES ('275845', '阳莺', '女', NULL, '教师', NULL);
INSERT INTO `teacher` VALUES ('423859', '耿丽萍', '女', '1968-04-04', '教师', NULL);
INSERT INTO `teacher` VALUES ('743586', '钟雪', '女', NULL, '教师', NULL);
INSERT INTO `teacher` VALUES ('846023', '胡清钟', '男', NULL, '教师', '');
INSERT INTO `teacher` VALUES ('872865', '杨兵', '男', NULL, '教师', NULL);

-- ----------------------------
-- View structure for course_manage_view
-- ----------------------------
DROP VIEW IF EXISTS `course_manage_view`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `course_manage_view` AS select `tea_course`.`course_id` AS `course_id`,`tea_course`.`course_code` AS `course_code`,`course`.`course_name` AS `course_name`,`course`.`course_credit` AS `course_credit`,`course`.`course_date` AS `course_date`,`tea_course`.`course_time` AS `course_time`,`course`.`course_type` AS `course_type`,`teacher`.`teacher_name` AS `teacher_name`,`teacher`.`teacher_title` AS `teacher_title` from ((`tea_course` left join `course` on((`tea_course`.`course_code` = `course`.`course_code`))) left join `teacher` on((`tea_course`.`teacher_id` = `teacher`.`teacher_id`)));

-- ----------------------------
-- Triggers structure for table stu_course
-- ----------------------------
DROP TRIGGER IF EXISTS `insert_with_semester`;
delimiter ;;
CREATE TRIGGER `insert_with_semester` BEFORE INSERT ON `stu_course` FOR EACH ROW BEGIN
	DECLARE seme VARCHAR(18) ;
	DECLARE uncode VARCHAR(21);
	DECLARE cou_code VARCHAR(11);
	IF(MONTH(now()) > 2 && MONTH(now()) < 8) THEN
		SET seme = CONCAT(substring(YEAR(now()) - 1 from 3 FOR 4), 2);
	ELSE
		SET seme = CONCAT(substring(YEAR(now()) - 1 from 3 FOR 4), 1);
	END IF;
	SET cou_code = (SELECT course_code FROM tea_course WHERE course_id = NEW.course_id);
	SET uncode = CONCAT(NEW.student_id, cou_code);
	SET NEW.semester = seme, NEW.unique_code = uncode;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table stu_course
-- ----------------------------
DROP TRIGGER IF EXISTS `insert_with_capacity`;
delimiter ;;
CREATE TRIGGER `insert_with_capacity` AFTER INSERT ON `stu_course` FOR EACH ROW BEGIN
	DECLARE cap INT;
	SET cap = (SELECT COUNT(course_id) FROM stu_course WHERE stu_course.course_id = NEW.course_id);
	IF(cap > (SELECT capacity FROM tea_course WHERE course_id = NEW.course_id)) THEN
		DELETE FROM stu_course WHERE course_id = NEW.course_id;
	END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table student
-- ----------------------------
DROP TRIGGER IF EXISTS `auto_insert_login`;
delimiter ;;
CREATE TRIGGER `auto_insert_login` AFTER INSERT ON `student` FOR EACH ROW BEGIN
DECLARE pwd VARCHAR(6);
set pwd = floor(0+rand()*10);
WHILE length(pwd) < 6 DO
	SET pwd = (SELECT CONCAT(pwd,floor(0+rand()*10)));
END WHILE;
INSERT INTO login_info(UID,password,status) VALUES(new.student_id, pwd, '学生');
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
