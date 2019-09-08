package com.sz.controller;


import com.sz.pojo.Comments;
import com.sz.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/comments")
public class CommentsController {

    @Autowired
    private CommentsService commentsService;

    @RequestMapping(value = "/addComments")
    @ResponseBody
    public Map<String, String> addUserComments(@RequestBody Comments comments) {
        Map<String, String> resultComments = new HashMap<>();

        System.out.println(comments);
        commentsService.saveComments(comments);
        resultComments.put("success","success");

        return resultComments;
    }

    @RequestMapping(value = "/allComments")
    @ResponseBody
    public List<Comments> findAllUserComments() {
        List<Comments> commentsList = new ArrayList<>();
        commentsList = commentsService.findAllUserComments();

        System.out.println("所有评论："+commentsList);

        return commentsList;
    }

}
