package com.javan.controller;

import com.javan.dao.*;
import com.javan.entity.*;
import com.javan.service.OrderItemService;
import com.javan.service.OrderService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.javan.controller.PdfUtils;

import java.io.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
class UtilsController {
    @Autowired
    private OrderItemService ois;
    @Autowired
    private OrderService fs;
    @Autowired
    private OrderItemMapper oim;
    @Autowired
    private ClothMapper cm;
    @Autowired
    private OrderMapper om;
    @Autowired
    private CancelMapper canm;
    @Autowired
    private CancelItemMapper cim;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String t() {
        return "discard";
    }

    @RequestMapping(value = "/sellcondition", method = RequestMethod.GET)
    public String test() {
        return "sellcondition";
    }

    @RequestMapping(value = "/countamount", method = RequestMethod.GET)
    public String countamount() {
        return "countamount";
    }

    @RequestMapping(value = "/historysummary", method = RequestMethod.GET)
    public String historysummary() {
        return "historysummary";
    }

    @ResponseBody
    @RequestMapping(value = "/history", method = {RequestMethod.GET,RequestMethod.POST})
    public List<Object> b(@DateTimeFormat(pattern = "yyyy-MM-dd")Date startTime,
                         @DateTimeFormat(pattern = "yyyy-MM-dd") Date endTime) {
        List<Object> l=new ArrayList<Object>();
        OrderExample oe=new OrderExample();
        oe.createCriteria().andDateBetween(startTime,endTime).andStatusEqualTo("complete");
        l.addAll(om.selectByExample(oe));
        CancelExample ce=new CancelExample();
        ce.createCriteria().andDateBetween(startTime,endTime).andStatusEqualTo("complete");
        l.addAll(canm.selectByExample(ce));
        return l;
    }

    @ResponseBody
    @RequestMapping(value = "/statistics", method = {RequestMethod.GET,RequestMethod.POST})
    public List<Statistics> A(@RequestParam("startTime") @DateTimeFormat(pattern = "yyyy-MM-dd")Date startTime,        //统计指定顾客在某时间段内的衣服购买数据
                              @RequestParam("endTime")@DateTimeFormat(pattern = "yyyy-MM-dd") Date endTime,
                              @RequestParam("customer_name")String customer_name)  {
        String customer_order="";
        ArrayList<Statistics> l=new ArrayList<Statistics>();
        Integer amout=0;
        Float total=0f;
        if(customer_name!=null&&customer_name.length()!=0){                                //获取对应顾客单号
            OrderExample oe=new OrderExample();
            oe.createCriteria().andCustomer_nameEqualTo(customer_name).andStatusEqualTo("complete");
            List<Order> lo=om.selectByExample(oe);
            if(lo.size()>0) {
                for (int i = 0; i < lo.size() - 1; i++) {
                    customer_order += lo.get(i).getItem_id();
                    customer_order += ",";
                }
                customer_order += lo.get(lo.size() - 1).getItem_id();
            }
        }
        if(!(customer_name!=null&&customer_name.length()!=0)||customer_order.length()>0){                           // 统计订单数据 只在已输入用户名且用户不存在订单的情况下不执行
            List<Integer> a=oim.showCloth(startTime,endTime,customer_order);
            Integer tmp=0;
            Float tmp1=0f;
            for(int i:a){
                amout=0;
                total=0f;
                Statistics s=new Statistics();
                s.init();
                s.setCloth_id(i);
                s.setPicture_path(cm.selectByPrimaryKey(i)==null?"":cm.selectByPrimaryKey(i).getPicture_path());
                tmp=oim.countNumberByCondition(i,startTime,endTime,"s",customer_order);
                tmp1=oim.countAmountByCondition(i,startTime,endTime,"s",customer_order);
                if(tmp!=null&&tmp1!=null) {
                    s.setSamount(tmp);
                    s.setStotal(tmp1);
                    amout+=tmp;
                    total+=tmp1;
                }
                tmp=oim.countNumberByCondition(i,startTime,endTime,"m",customer_order);
                tmp1=oim.countAmountByCondition(i,startTime,endTime,"m",customer_order);
                if(tmp!=null&&tmp1!=null) {
                    s.setMamount(tmp);
                    s.setMtotal(tmp1);
                    amout+=tmp;
                    total+=tmp1;
                }
                tmp=oim.countNumberByCondition(i,startTime,endTime,"l",customer_order);
                tmp1=oim.countAmountByCondition(i,startTime,endTime,"l",customer_order);
                if(tmp!=null&&tmp1!=null) {
                    s.setLamount(tmp);
                    s.setLtotal(tmp1);
                    amout+=tmp;
                    total+=tmp1;
                }
                tmp=oim.countNumberByCondition(i,startTime,endTime,"xl",customer_order);
                tmp1=oim.countAmountByCondition(i,startTime,endTime,"xl",customer_order);
                if(tmp!=null&&tmp1!=null) {
                    s.setXlamount(tmp);
                    s.setXltotal(tmp1);
                    amout+=tmp;
                    total+=tmp1;
                }
                tmp=oim.countNumberByCondition(i,startTime,endTime,"2xl",customer_order);
                tmp1=oim.countAmountByCondition(i,startTime,endTime,"2xl",customer_order);
                if(tmp!=null&&tmp1!=null) {
                    s.setXl2amount(tmp);
                    s.setXl2total(tmp1);
                    amout+=tmp;
                    total+=tmp1;
                }
                tmp=oim.countNumberByCondition(i,startTime,endTime,"3xl",customer_order);
                tmp1=oim.countAmountByCondition(i,startTime,endTime,"3xl",customer_order);
                if(tmp!=null&&tmp1!=null) {
                    s.setXl3amount(tmp);
                    s.setXl3total(tmp1);
                    amout+=tmp;
                    total+=tmp1;
                }
                tmp=oim.countNumberByCondition(i,startTime,endTime,"4xl",customer_order);
                tmp1=oim.countAmountByCondition(i,startTime,endTime,"4xl",customer_order);
                if(tmp!=null&&tmp1!=null) {
                    s.setXl4amount(tmp);
                    s.setXl4total(tmp1);
                    amout+=tmp;
                    total+=tmp1;
                }
                tmp=oim.countNumberByCondition(i,startTime,endTime,"5xl",customer_order);
                tmp1=oim.countAmountByCondition(i,startTime,endTime,"5xl",customer_order);
                if(tmp!=null&&tmp1!=null) {
                    s.setXl5amount(tmp);
                    s.setXl5total(tmp1);
                    amout+=tmp;
                    total+=tmp1;
                }
                tmp=oim.countNumberByCondition(i,startTime,endTime,"other",customer_order);
                tmp1=oim.countAmountByCondition(i,startTime,endTime,"other",customer_order);
                if(tmp!=null&&tmp1!=null) {
                    s.setOtheramount(tmp);
                    s.setOthertotal(tmp1);
                    amout+=tmp;
                    total+=tmp1;
                }
                s.setTotal(total);
                s.setAmount(amout);
                l.add(s);
            }
        }
        customer_order="";
        if(customer_name!=null&&customer_name.length()!=0){
            CancelExample ce=new CancelExample();
            ce.createCriteria().andCustomer_nameEqualTo(customer_name).andStatusEqualTo("complete");
            List<Cancel> lo=canm.selectByExample(ce);
            if(lo.size()>0) {
                for (int i = 0; i < lo.size() - 1; i++) {
                    customer_order += lo.get(i).getItem_id();
                    customer_order += ",";
                }
                customer_order += lo.get(lo.size() - 1).getItem_id();
            }
        }
        if(!(customer_name!=null&&customer_name.length()!=0)||customer_order.length()>0){                          //// 统计退单数据
            List<Integer> b=cim.showCloth(startTime,endTime,customer_order);
            for(int i:b){
                Integer tmp=0;
                Float tmp1=0f;
                Statistics s=null;
                for(Statistics j:l){
                    if(j.getCloth_id()==i){
                        s=j;
                    }
                }
                if(s==null){
                    s=new Statistics();
                    s.init();
                    s.setCloth_id(i);
                    s.setPicture_path(cm.selectByPrimaryKey(i)==null?"":cm.selectByPrimaryKey(i).getPicture_path());
                    l.add(s);
                }
                tmp=cim.countNumberByCondition(i,startTime,endTime,"s",customer_order);
                tmp1=cim.countAmountByCondition(i,startTime,endTime,"s",customer_order);
                if(tmp!=null&&tmp1!=null) {
                    s.setSamount(s.getSamount()-tmp);
                    s.setStotal(s.getStotal()-tmp1);
                    amout+=tmp;
                    total+=tmp1;
                }
                tmp=cim.countNumberByCondition(i,startTime,endTime,"m",customer_order);
                tmp1=cim.countAmountByCondition(i,startTime,endTime,"m",customer_order);
                if(tmp!=null&&tmp1!=null) {
                    s.setMamount(s.getMamount()-tmp);
                    s.setMtotal(s.getMtotal()-tmp1);
                    amout+=tmp;
                    total+=tmp1;
                }
                tmp=cim.countNumberByCondition(i,startTime,endTime,"l",customer_order);
                tmp1=cim.countAmountByCondition(i,startTime,endTime,"l",customer_order);
                if(tmp!=null&&tmp1!=null) {
                    s.setLamount(s.getLamount()-tmp);
                    s.setLtotal(s.getLtotal()-tmp1);
                    amout+=tmp;
                    total+=tmp1;
                }
                tmp=cim.countNumberByCondition(i,startTime,endTime,"xl",customer_order);
                tmp1=cim.countAmountByCondition(i,startTime,endTime,"xl",customer_order);
                if(tmp!=null&&tmp1!=null) {
                    s.setXlamount(s.getXlamount()-tmp);
                    s.setXltotal(s.getXltotal()-tmp1);
                    amout+=tmp;
                    total+=tmp1;
                }
                tmp=cim.countNumberByCondition(i,startTime,endTime,"2xl",customer_order);
                tmp1=cim.countAmountByCondition(i,startTime,endTime,"2xl",customer_order);
                if(tmp!=null&&tmp1!=null) {
                    s.setXl2amount(s.getXl2amount()-tmp);
                    s.setXl2total(s.getXl2total()-tmp1);
                    amout+=tmp;
                    total+=tmp1;
                }
                tmp=cim.countNumberByCondition(i,startTime,endTime,"3xl",customer_order);
                tmp1=cim.countAmountByCondition(i,startTime,endTime,"3xl",customer_order);
                if(tmp!=null&&tmp1!=null) {
                    s.setXl3amount(s.getXl3amount()-tmp);
                    s.setXl3total(s.getXl3total()-tmp);
                    amout+=tmp;
                    total+=tmp1;
                }
                tmp=cim.countNumberByCondition(i,startTime,endTime,"4xl",customer_order);
                tmp1=cim.countAmountByCondition(i,startTime,endTime,"4xl",customer_order);
                if(tmp!=null&&tmp1!=null) {
                    s.setXl4amount(s.getXl4amount()-tmp);
                    s.setXl4total(s.getXl4total()-tmp);
                    amout+=tmp;
                    total+=tmp1;
                }
                tmp=cim.countNumberByCondition(i,startTime,endTime,"5xl",customer_order);
                tmp1=cim.countAmountByCondition(i,startTime,endTime,"5xl",customer_order);
                if(tmp!=null&&tmp1!=null) {
                    s.setXl5amount(s.getXl5amount()-tmp);
                    s.setXl5total(s.getXl5total()-tmp);
                    amout+=tmp;
                    total+=tmp1;
                }
                tmp=cim.countNumberByCondition(i,startTime,endTime,"other",customer_order);
                tmp1=cim.countAmountByCondition(i,startTime,endTime,"other",customer_order);
                if(tmp!=null&&tmp1!=null) {
                    s.setOtheramount(s.getOtheramount()-tmp);
                    s.setOthertotal(s.getOthertotal()-tmp);
                    amout+=tmp;
                    total+=tmp1;
                }
                s.setTotal(s.getTotal()-total);
                s.setAmount(s.getAmount()-amout);
            }

        }
        return l;
    }

    @RequestMapping(value="/clothcount",method = RequestMethod.POST)
    @ResponseBody
    public Statistics statistics(@Param("cloth_id") Integer cloth_id, @RequestParam("startTime") @DateTimeFormat(pattern = "yyyy-MM-dd")Date startTime,    // 统计某衣服在指定时间内的销量
                                 @RequestParam("endTime")@DateTimeFormat(pattern = "yyyy-MM-dd") Date endTime){
        Statistics s=new Statistics();
        Float tmp,tmp1;
        Integer tmp2,tmp3;
        tmp=oim.countAmountByCondition(cloth_id,startTime,endTime,null,null);
        tmp1=cim.countAmountByCondition(cloth_id,startTime,endTime,null,null);
        tmp=tmp==null?0f:tmp;
        tmp1=tmp1==null?0f:tmp1;
        s.setTotal(tmp-tmp1);
        tmp2= oim.countNumberByCondition(cloth_id,startTime,endTime,null,null);
        tmp3=cim.countNumberByCondition(cloth_id,startTime,endTime,null,null);
        tmp2=tmp2==null?0:tmp2;
        tmp3=tmp3==null?0:tmp3;
        s.setAmount(tmp2-tmp3);
        return s;
    }

    @RequestMapping(value="/orderpdf/{id}",method = RequestMethod.GET)

    public void pdftest(HttpServletRequest request, HttpServletResponse response,@PathVariable Integer id) throws Exception {
        Order o=fs.getByid(id);
        String tmp=o.getItem_id();
        String[] s=tmp.split(",");
        Map<String,String> m=new HashMap<String, String>();
        Calendar ca=Calendar.getInstance();
        ca.setTime(o.getDate());
        m.put("customer_name",o.getCustomer_name());
        m.put("address",o.getAddress());
        m.put("phone_number",o.getPhone_number());
        m.put("year", ""+(ca.get(Calendar.YEAR)-2000));
        m.put("month",""+(ca.get(Calendar.MONTH)+1));
        m.put("day",""+ca.get(Calendar.DAY_OF_MONTH));
        m.put("mail_fee",o.getDes());                                 //填入订单基本信息

        int tmp1=1;
        Float f=0f;
        List<OrderItem> l=new ArrayList<OrderItem>();
        Set<Integer> indexset=new HashSet<Integer>();
        for(String i:s){
            OrderItem tmp2=ois.getByid(Integer.parseInt(i));
            l.add(tmp2);
            indexset.add(tmp2.getCloth_id());
        }
        Object[] index=indexset.toArray();
        for(OrderItem oi:l){
            for(int j=0;j<index.length;j++){
                if((Integer)index[j]==oi.getCloth_id()){
                    tmp1=j+1;
                }
            }
            m.put("cloth_id"+tmp1,""+oi.getCloth_id());
            m.put("color"+tmp1,""+oi.getColor());
            m.put("check"+oi.getSize()+tmp1,""+oi.getNum());
            m.put("single"+tmp1,""+oi.getSingle());
            m.put("total"+tmp1,""+(Float.parseFloat(m.get("total"+tmp1)==null?"0":m.get("total"+tmp1))+oi.getTotal()));
            m.put("num"+tmp1,""+(Integer.parseInt(m.get("num"+tmp1)==null?"0":m.get("num"+tmp1))+oi.getNum()));
            m.put("des"+tmp1,oi.getDes());
            f+=oi.getTotal();                                           //填入订单详细信息并计算总金额
        }
        Float.parseFloat("0");
        int a=o.getDes().length();
        m.put("mail_fee",""+(o.getDes().length()==0?0f:Float.parseFloat(o.getDes())));
        f+=o.getDes().length()==0?0f:Float.parseFloat(o.getDes());
        PdfUtils.addToMap(m,f);

        m.put("total_num",""+f);           //填入总金额

        try {
            String url="/home/123.pdf";                                          //String url="/home/123.pdf"; "C:\\Users\\Administrator\\Desktop\\123.pdf";(url,"C:\\Users\\Administrator\\Desktop\\simhei.ttf",m);
            ByteArrayOutputStream byteArrayOutputStream=PdfUtils.generatePdfStream(url,"/home/simhei.ttf",m);                                        //( url,"/home/simhei.ttf",m);
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Length", "" + byteArrayOutputStream.size());
            response.setContentType("application/pdf");
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            toClient.write(byteArrayOutputStream.toByteArray());
            toClient.flush();
            toClient.close();                              //生成并返回pdf文件
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Status status=new Status();
        status.setstatus(200);
    }
}