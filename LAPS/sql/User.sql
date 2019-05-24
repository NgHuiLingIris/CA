CREATE TABLE `sa48`.`user` (
  `employeeid` INT NOT NULL,
  `employeename` VARCHAR(50) NULL,
  `employeediv` VARCHAR(50) NULL,
  `employeecontact` INT(15) NULL,
  `employeemail` VARCHAR(50) NULL,
`reportsto` VARCHAR(100) NULL,
	`leaveentitled` VARCHAR(50) NULL,
	`comphours` DOUBLE NOT NULL,
  PRIMARY KEY (`employeeid`));