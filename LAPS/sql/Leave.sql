CREATE TABLE `leave_app` (
  `id` int(11) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `leave_type` varchar(255) NOT NULL,
  `from_date` date DEFAULT NULL,
  `granularity` double NOT NULL,
  `manager_comment` varchar(255) DEFAULT NULL,
  `overseas_contact_details` int(11) NOT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT 'PENDING',
  `to_date` date DEFAULT NULL,
  `reports_to` bigint(20) DEFAULT NULL,
  `employee_id` int(11) NOT NULL,
  `duration` double DEFAULT NULL,
  `employeename` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
