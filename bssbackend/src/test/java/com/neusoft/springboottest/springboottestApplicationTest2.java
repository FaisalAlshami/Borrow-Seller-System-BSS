package com.neusoft.springboottest;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neusoft.springboottest.user.dto.EmpDept;
import com.neusoft.springboottest.user.entity.Dept;
import com.neusoft.springboottest.user.entity.Emp;
import com.neusoft.springboottest.user.entity.SysUser;
import com.neusoft.springboottest.user.mapper.EmpMapper;
import com.neusoft.springboottest.user.mapper.SysUserMapper;


import com.neusoft.springboottest.user.service.IDeptService;
import com.neusoft.springboottest.user.service.IEmpService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class springboottestApplicationTest2 {
//    Mapper CRUD

    @Autowired
    SysUserMapper sysUserMapper;

    @Test
    public void method101(){
        SysUser user= sysUserMapper.selectById(1);
        System.out.println(user);
        System.out.println("aaaaa");
    }
    @Test
    public void method102(){
        ArrayList idList=   new ArrayList<>();
        idList.add(1);
        idList.add(2);
        idList.add(3);
        List<SysUser> userList= sysUserMapper.selectBatchIds(idList);
        for(SysUser user:userList){
            System.out.println(user);
        }
    }
    @Test
    public void method1021(){
       List IdsList= Arrays.asList(1,2,3);
        List<SysUser> userList= sysUserMapper.selectBatchIds(IdsList);
        userList.forEach(System.out::println);
    }
    @Test
    public void method1022(){
        List IdsList= Arrays.asList(1,2,3);
        List<SysUser> userList= sysUserMapper.selectBatchIds(IdsList);
        Iterator i=userList.iterator();
        while(i.hasNext()){
            System.out.println(i.next());
        }
    }

    //   username password
    @Test
    public void  method103(){
        Map mapParam=new HashMap<>();
        mapParam.put("username","admin");
        mapParam.put("password","admin");
        List<SysUser> userList=  sysUserMapper.selectByMap(mapParam);
        userList.forEach(System.out::println);

    }

    //insert entity data to database
    @Test
    public void method104(){

        SysUser user=new SysUser();
        user.setUsername("king");
        user.setPassword("123");
        int num= sysUserMapper.insert(user);
        if(num==1){
            System.out.println("insert successful");
        }else{
            System.out.println("insert failure");
        }

    }
//    1.username like '%a%' and number less than 5
//    username like "%a%"
    @Test
    public void method201(){
        //condition constructor
//        we  user querywrapper
//        QueryWrapper<SysUser> qw1=new QueryWrapper<SysUser>();
        QueryWrapper<SysUser> qw2=  Wrappers.query();
        qw2.like("username","a").lt("number","5");
        List<SysUser> userList= sysUserMapper.selectList(qw2);
        userList.forEach(System.out::println);
    }

//    2.username like '%e%' and last_login between '2021-01-10' and '2021-01-26' and name is not null
        @Test
        public void method202(){
            QueryWrapper<SysUser> qw2=  Wrappers.query();
            qw2.like("username","e").between("last_login","2021-01-10","2021-01-26");
            List<SysUser> userList= sysUserMapper.selectList(qw2);
            userList.forEach(System.out::println);

        }
//  3.  name like 'j%' or number >= 5 order by number desc,userid asc
    @Test
    public void method203(){
        QueryWrapper<SysUser> qw2=  Wrappers.query();
        qw2.likeRight("username","j").or().ge("number",5);
        List<SysUser> userList= sysUserMapper.selectList(qw2);
        userList.forEach(System.out::println);
    }

//    java.lang.AbstractMethodError: Method com/mysql/jdbc/JDBC4ResultSet.getObject(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object; is abstract
//    4.here we find we need user    version 8
//            *  The hiredate time is 1981-05-01 and the immediate superior’s name  first letter is k
//     * date_format(create_time,'%Y-%m-%d') and mgr in (select empno from user where ename like 'k%')
//    select * from emp where  date_formate( hiredate,'%Y-%m-%d')='1981-05-01'  and mgr in(select empno from user where ename like 'k%')
    @Autowired
    EmpMapper empMapper;

    @Test
    public void method301(){
        //sql injection     ---jdbc -statement  soleve ---> preparedStatement
//          ----->
        String datestr="1981-05-01";
        QueryWrapper<Emp> qw=  Wrappers.query();
//        qw.apply("date_format( hiredate,'%Y-%m-%d')="+datestr)
//                .inSql("mgr","select empno from emp where ename like 'k%'");
                qw.apply("date_format( hiredate,'%Y-%m-%d')= {0}",datestr)
                .inSql("mgr","select empno from emp where ename like 'k%'");
        List<Emp> userList= empMapper.selectList(qw);
        userList.forEach(System.out::println);
    }
//    * 5. The first letter is s  and (the sal is less than 2000 or the comm is not empty)
//     * ename like 's%' and (sal < 2000 or comm is not null)

    @Test
    public void method303(){
        //sql injection     ---jdbc -statement  soleve ---> preparedStatement
//          ----->
        QueryWrapper<Emp> qw=  Wrappers.query();
        qw.likeRight("ename",'s').and(qw2->qw2.lt("sal","2000") .or().isNotNull("comm"));
        List<Emp> userList= empMapper.selectList(qw);
        userList.forEach(System.out::println);
    }
//* 6.The first letter is s’or (the sal is less than 2000 and greater than 1000 and the comm is not empty) )
//            * ename like 's%' or (sal < 2000 and sal > 1000 and comm is not null)
    @Test
    public void method304(){
        QueryWrapper<Emp> qw=  Wrappers.query();
        qw.likeRight("ename",'s').or(qw2->qw2.lt("sal","2000") .gt("sal","1000").isNotNull("comm"));
        List<Emp> userList= empMapper.selectList(qw);
        userList.forEach(System.out::println);
    }

//7.(The sal is less than 2000 or the comm is not empty) and the first letter is s
//     * (sal < 2000 or comm is not null) and name like 'm%'

    @Test
    public void method305(){
        QueryWrapper<Emp> qw=  Wrappers.query();
        qw.nested(qw2->qw2.lt("sal","2000").or().isNotNull("comm")).likeRight("ename","m");
        List<Emp> userList= empMapper.selectList(qw);
        userList.forEach(System.out::println);
    }
// 8.The empno is 7369,7499,7521
//            * empno in (7369,7499,7521)
//    9.Only one sentence that meets the conditions can be returned
//     * limit 1
//    (return only empno and ename)
    @Test
    public void method306(){
        List IdsList= Arrays.asList(7369,7499,7521);
        QueryWrapper<Emp> qw=  Wrappers.query();;
        qw.in("empno",IdsList).last("limit 0,1").select("empno","ename");
        List<Emp> userList= empMapper.selectList(qw);
        userList.forEach(System.out::println);
    }


//            * 10_2.The name contains  m and the sal is less than 3000 ( comm , deptno  are not  returned)
//            * ename like '%m%' and sal < 3000
    @Test
    public void method307(){
        List IdsList= Arrays.asList(7369,7499,7521);
        QueryWrapper<Emp> qw=  Wrappers.query();;
        qw.in("empno",IdsList).last("limit 0,1").select(Emp.class,
                info->{
                    System.out.println(info);
                    if(info.getColumn().equalsIgnoreCase("comm") || info.getColumn().equalsIgnoreCase("deptno") ) {
                        return false;
                    }else{
                        return true;
                    }

            });
        List<Emp> userList= empMapper.selectList(qw);
        userList.forEach(System.out::println);

    }

// 11.Grouped by the deptno, query the average sal, maximum sal, and minimum sal of each group.
//     * And only take the groups whose total sal is less than 10000.
//    select sum(sal),avg(sal),max(sal),min(sal) from emp
//    group by deptno
//    having sum(sal)<10000
        @Test
        public void method308(){
            QueryWrapper<Emp> qw=  Wrappers.query();;
            qw.select("sum(sal)","avg(sal)","max(sal)","min(sal)").
                    groupBy("deptno").having("sum(sal)<{0}",10000);

            List<Map<String, Object>> userList= empMapper.selectMaps(qw);
            userList.forEach(System.out::println);

        }


     @Test
        public void method401() {
        //  Conditional constructor
        QueryWrapper<Emp> userQuery = new QueryWrapper<>();
        userQuery.select("empno", "ename").like("ename", "s").lt("sal", 4000);

        List<Object> userList = empMapper.selectObjs(userQuery);
        userList.forEach(System.out::println);
    }


     @Test
    public void select402() {
        //  Conditional constructor
        QueryWrapper<Emp> userQuery = new QueryWrapper<>();
        userQuery.like("ename", "s").lt("sal", 4000);

        Integer count = empMapper.selectCount(userQuery);
        System.out.println("total：" + count);
    }


     @Test
    public void select403() {
         String username = "blake";
         String password = "123";
         //  Conditional constructor
         QueryWrapper<SysUser> userQuery = new QueryWrapper<>();
         userQuery.eq("username", username).eq("password", password);
         SysUser user = null;
         try {
             user = sysUserMapper.selectOne(userQuery);

             if (user == null) {
                 System.out.println("login failure wrong username or password");
             } else {
                 System.out.println(user);
                 System.out.println("login successful");
             }

         } catch (MyBatisSystemException e) {
             System.out.println("login failure");
         }

     }
          @Test
         public void select404() {
             Emp userWhere = new Emp();
             userWhere.setEname("s");
             userWhere.setSal(5000.0);

             //  Conditional constructor
             QueryWrapper<Emp> userQuery = new QueryWrapper<>(userWhere);
             // userQuery.like("ename", "a").lt("sal", 5000);

             List<Emp> userList = empMapper.selectList(userQuery);
             userList.forEach(System.out::println);
         }

     @Test
    public void select405() {
        Map<String, Object> params = new HashMap<>();
        params.put("ename", "king");
        params.put("sal", 5000);

        //  Conditional constructor
        QueryWrapper<Emp> userQuery = new QueryWrapper<>();
         userQuery.allEq(params);
//        userQuery.allEq((k, v) -> !k.equals("ename"), params);

        List<Emp> userList = empMapper.selectList(userQuery);
        userList.forEach(System.out::println);
    }



     @Test
    public void select406() {
        //  Conditional constructor
        LambdaQueryWrapper<Emp> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.like(Emp::getEname, "s").lt(Emp::getSal, 4000);

        List<Emp> userList = empMapper.selectList(lambdaQueryWrapper);
        userList.forEach(System.out::println);
    }

     @Test
    public void select407() {
        //  Conditional constructor
        LambdaQueryWrapper<Emp> lambdaQuery = new LambdaQueryWrapper<>();
        lambdaQuery.likeRight(Emp::getEname, "s").and(age -> age.lt(Emp::getSal, 4000).or().isNotNull(Emp::getComm));

        List<Emp> userList = empMapper.selectList(lambdaQuery);
        userList.forEach(System.out::println);
    }


     @Test
    public void select408() {
        //  Conditional constructor
        List<Emp> userList = new LambdaQueryChainWrapper<Emp>(empMapper).like(Emp::getEname, "s")
                .ge(Emp::getSal, 500).list();
        userList.forEach(System.out::println);
    }

    // use methods which defined outself
     @Test
    public void select409() {
        LambdaQueryWrapper<Emp> lambda = new QueryWrapper<Emp>().lambda();
        lambda.likeRight(Emp::getEname, "s").and(sal -> sal.lt(Emp::getSal, 4000).or().isNotNull(Emp::getComm));

        List<Emp> userList = empMapper.selectAll2(lambda);
        userList.forEach(System.out::println);
    }

     @Test
    public void selectListPage() {
        //  Conditional constructor
        QueryWrapper<Emp> userQuery = Wrappers.query();
        userQuery.ge("sal", 300);
        Page<Emp> page = new Page<>(2, 3);
        IPage<Emp> userPage = empMapper.selectPage(page, userQuery);
        System.out.println("pages num：" + userPage.getPages());
        System.out.println("total ：" + userPage.getTotal());
        List<Emp> userList = userPage.getRecords();
        userList.forEach(System.out::println);
    }

     @Test
    public void selectMapsPage() {
        //  Conditional constructor
        QueryWrapper<Emp> userQuery = Wrappers.query();
        userQuery.ge("sal", 300);

        IPage<Map<String, Object>> page = new Page<>(1, 3,false);

        IPage<Map<String, Object>> userPage = empMapper.selectMapsPage(page, userQuery);
        System.out.println("total pages num：" + userPage.getPages());
        System.out.println("total：" + userPage.getTotal());

        List<Map<String, Object>> userList = userPage.getRecords();
        userList.forEach(System.out::println);
    }

    @Test
    public void selecMypage() {
        //  Conditional constructor
        QueryWrapper<Emp> userQuery = Wrappers.query();
        userQuery.ge("sal", 300);

        Page<Emp> page = new Page<>(1, 3);

        IPage<Emp> userPage = empMapper.selectUserPage(page, userQuery);
        System.out.println("total pages num：" + userPage.getPages());
        System.out.println("total：" + userPage.getTotal());

        List<Emp> userList = userPage.getRecords();
        userList.forEach(System.out::println);
    }


     @Test
    public void testCondition() {
        String name = null;
        String job = "m";
        condition(name, job);
    }

    private void condition(String name, String job) {
        QueryWrapper<Emp> userQuery = new QueryWrapper<>();
        userQuery.like(StringUtils.isNotBlank(name), "ename", name).like(StringUtils.isNotBlank(job), "job", job);
        List<Emp> userList = empMapper.selectList(userQuery);
        userList.forEach(System.out::println);
    }


     @Test
    public void updateById() {
        Emp user = new Emp();
        user.setEmpno(7369);
        user.setJob("no job");
        int rows = empMapper.updateById(user);
        System.out.println("影响记录数：" + rows);
    }

     @Test
    public void updateByWrapper1() {
        UpdateWrapper<Emp> userWrapper = Wrappers.update();
        userWrapper.eq("ename", "smith");
        Emp user = new Emp();
        user.setJob("salesman");
        int rows = empMapper.update(user, userWrapper);
        System.out.println("influence rows：" + rows);
    }

    // querywrapper param and entity param  occur
     @Test
    public void updateByWrapper2() {
        Emp userWhere = new Emp();
        userWhere.setEname("smith");

        UpdateWrapper<Emp> userWrapper = Wrappers.update(userWhere);
        userWrapper.eq("ENAME", "smith").lt("sal", 5000);

        Emp user = new Emp();
        user.setEname("tom");

        int rows = empMapper.update(user, userWrapper);
        System.out.println("影响记录数：" + rows);
    }

    @Autowired
    IEmpService iEmpService;


    @Test
    public void getOne() {

        Emp one = iEmpService.getOne(Wrappers.<Emp>lambdaQuery().eq(Emp::getEname, "king"));
        System.out.println(one);
    }

//    insert primarykey by ourself use type IdType.INPUT)
//    @TableId(value = "EMPNO", type = IdType.INPUT)
    @Test
    public void insertBatch(){
        Emp e1=new Emp();
        e1.setEmpno(1001);
        e1.setEname("tom");
        Emp e2=new Emp();
        e2.setEmpno(1002);
        e2.setEname("jerry");
        List<Emp> userList = Arrays.asList(e1, e2);
      boolean ok=  iEmpService.saveBatch(userList);
        System.out.println(ok);
    }

    @Test
    public void insertOrUpdateBatch() {
        Emp e1=new Emp();

        e1.setEmpno(1001);
        e1.setEname("tom333");
        Emp e2=new Emp();
        e2.setEmpno(1003);
        e2.setEname("jerry333");

        List<Emp> userList = Arrays.asList(e1, e2);
        boolean ok=  iEmpService.saveOrUpdateBatch(userList);
        System.out.println(ok);
    }

//    Emp
    //select * from emp where sal>1000 and ename like '%s%'
    @Test
    public void selectChain() {
        List<Emp> userList = iEmpService.lambdaQuery().gt(Emp::getSal, 1000).like(Emp::getEname, "s").list();
        userList.forEach(System.out::println);
    }

    @Test
    public void selectChain2() {
        Emp emp = iEmpService.lambdaQuery().gt(Emp::getSal, 1000).like(Emp::getEname, "s").one();
        System.out.println(emp);
    }

    @Test
    public void insert() {
        Emp e1=new Emp();

        e1.setEmpno(1004);
        e1.setEname("tom44444");
        boolean flag = e1.insert();
        System.out.println("flag：" + flag);
    }
    @Test
    public void uuidtest(){
        for(int i=0;i<10;i++){

//           System.out.println(UUID.randomUUID());
            String uuid1=UUID.randomUUID().toString();
            System.out.println( uuid1.replaceAll("-", ""));

        }
    }
    @Test
    public void method501(){
       Emp e= iEmpService.getEmpByid(7782);
        System.out.println(e);
    }
    @Test
    public void method502(){
        EmpDept e= iEmpService.getEmpByid2(7782);
        System.out.println(e);
    }

    @Autowired
    IDeptService iDeptService;

    @Test
    public void method602(){
        Dept  d= iDeptService.getDeptByid(10);
        System.out.println(d);
    }


//    @Test
//    public void getOne2() {
//
//        Emp one = iEmpService.getOne(Wrappers.<Emp>lambdaQuery().eq(Emp::getEname, "king"));
//        System.out.println(one);
//    }


}




