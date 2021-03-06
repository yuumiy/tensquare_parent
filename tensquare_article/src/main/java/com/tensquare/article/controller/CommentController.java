package com.tensquare.article.controller;

import com.tensquare.article.pojo.Comment;
import com.tensquare.article.service.CommentService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private RedisTemplate redisTemplate;

    //PUT /comment/thumbup/{commentId} 根据评论id点赞评论
    @RequestMapping(value = "/thumbup/{commentId}", method = RequestMethod.PUT)
    public Result thumbup(@PathVariable String commentId) {
        //把用户点赞信息保存到Redis中
        //每次点赞之前，先查询用户点赞信息
        //如果没有点赞信息，用户可以点赞
        //如果有点赞信息，用户不能重复点赞

        //模拟用户id
        String userid = "1111";

        //在redis中查询用户是否已经点赞
        Object result = redisTemplate.opsForValue().get("thumbup_" + userid + "_" + commentId);

        //如果点赞不能重复点赞
        if (result != null) {
            return new Result(false, StatusCode.REMOTEERROR, "不能重复点赞");
        }

        //如果没有点赞，可以进行点赞操作
        commentService.thumbup(commentId);

        //保存点赞记录
        redisTemplate.opsForValue().set("thumbup_" + userid + "_" + commentId, 1);

        return new Result(true, StatusCode.OK, "点赞成功");

    }

    //GET /comment/article/{articleId} 根据文章id查询文章评论
    @RequestMapping(value = "/article/{articleId}", method = RequestMethod.GET)
    public Result findByArticleId(@PathVariable String articleId) {
        List<Comment> list = commentService.findByArticleId(articleId);
        return new Result(true, StatusCode.OK, "查询成功", list);
    }

    //GET /comment 查询所有评论
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        List<Comment> list = commentService.findAll();

        return new Result(true, StatusCode.OK, "查询成功", list);
    }


    //GET /comment/{commentId} 根据评论id查询评论数据
    @RequestMapping(value = "/{commentId}", method = RequestMethod.GET)
    public Result findById(@PathVariable String commentId) {
        Comment comment = commentService.findById(commentId);
        return new Result(true, StatusCode.OK, "查询成功", comment);
    }

    //POST /comment 新增评论
    //要把json转换为对象，所以要加@RequestBody
    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Comment comment) {
        commentService.save(comment);
        return new Result(true, StatusCode.OK, "新增成功");
    }

    //PUT /comment/{commentId} 修改评论
    @RequestMapping(value = "/{commentId}", method = RequestMethod.PUT)
    public Result updateById(@PathVariable String commentId, @RequestBody Comment comment) {
        //设置评论主键
        comment.set_id(commentId);
        //执行修改
        commentService.updateById(comment);

        return new Result(true, StatusCode.OK, "修改成功");
    }

    //DELETE /comment/{commentId} 根据id删除评论
    @RequestMapping(value = "/{commentId}", method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable String commentId) {
        commentService.deleteById(commentId);
        return new Result(true, StatusCode.OK, "删除成功");
    }

}
