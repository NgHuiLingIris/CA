CREATE TABLE `leave_app` (
  `id` varchar(100) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `leave_type` varchar(255) NOT NULL,
  `from_date` date DEFAULT NULL,
  `granularity` double DEFAULT NULL,
  `leave_entitlement` int(11) DEFAULT NULL,
  `manager_comment` varchar(255) DEFAULT NULL,
  `overseas_contact_details` int(11) DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `to_date` date DEFAULT NULL,
  `reportsTo` int(11) DEFAULT NULL,
  `reports_to` bigint(20) DEFAULT NULL,
  `employee_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
