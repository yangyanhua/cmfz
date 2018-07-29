package com.yangyh.controller;

import com.yangyh.entity.Journal;
import com.yangyh.service.JournalService;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalController {
    @Autowired
    private JournalService journalService;

    @RequestMapping("findjournal.do")
    public Map<String,Object> findjournal(Integer page,Integer rows){
        List<Journal> list = journalService.ServicePaging(page,rows);
        Integer integer = journalService.ServicetotalCounts();
        System.out.println("这是list:"+list);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", integer);
        map.put("rows", list);
        return map;
    }
    //删除
    @RequestMapping("/delete.do")
    public String  delete(Journal journal){
        journalService.Servicedelete(journal);
        return "ok";
    }

    //批量删除
    @RequestMapping("/pldelete.do")
    public String pldelete(String myLists) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        List<Integer> readValue = objectMapper.readValue(myLists, List.class);
        journalService.ServiceBatchdeletion(readValue);
        return "213";
    }

}
