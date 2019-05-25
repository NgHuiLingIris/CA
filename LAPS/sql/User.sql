CREATE TABLE `user1` (
  `employeeid` bigint(20) NOT NULL,
  `comphours` double NOT NULL,
  `employeecontact` bigint(20) NOT NULL,
  `employeediv` varchar(255) DEFAULT NULL,
  `employeemail` varchar(255) DEFAULT NULL,
  `employeename` varchar(255) DEFAULT NULL,
  `leaveentitled` float DEFAULT NULL,
  `reportsto` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`employeeid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
SELECT * FROM sa48.user1;