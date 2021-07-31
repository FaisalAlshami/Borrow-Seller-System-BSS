package com.neusoft.springboottest.user.dto;

import lombok.Data;

import java.util.Date;

@Data
public class EmpDept {

    private Integer empno;

    private String ename;

    private String job;

    private Integer mgr;

    private Date hiredate;

    private Double sal;

    private Double comm;

    private Integer deptno;
//    private Integer deptno;

    private String dname;

    private String loc;


}
