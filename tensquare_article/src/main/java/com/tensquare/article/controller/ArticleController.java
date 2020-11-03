package com.tensquare.article.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.tensquare.article.pojo.Article;
import com.tensquare.article.service.ArticleService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/article")
@CrossOrigin      //跨域问题的处理
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/exception",method = RequestMethod.GET)
    public Result test(){
        int a=1/0;
        return null;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        List list=articleService.findAll();
        return new Result(true, StatusCode.OK,"查询成功",list);
    }

    @RequestMapping(value = "/{articleId}",method = RequestMethod.GET)
    public Result findById(@PathVariable String articleId){
        Article article=articleService.findById(articleId);
        return new Result(true, StatusCode.OK,"查询成功",article);
    }

    @RequestMapping(method = RequestMethod.POST)
    //加了RequestBody注解，才能将json转为具体的对象
    public Result save(@RequestBody Article article){
        articleService.save(article);
        return new Result(true, StatusCode.OK,"新增成功");
    }

    @RequestMapping(value = "/{articleId}",method = RequestMethod.PUT)
    public Result updateById(@PathVariable String articleId,
                             @RequestBody Article article){
        article.setId(articleId);
        articleService.updateById(article);
        return new Result(true, StatusCode.OK,"修改成功");
    }

    @RequestMapping(value = "/{articleId}",method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable String articleId){
        articleService.deleteById(articleId);
        return new Result(true, StatusCode.OK,"删除成功");
    }

    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    //之前接受文章数据，使用pojo，但是现在根据条件查询
    //而所有的条件都需要进行判断，遍历pojo的所有属性需要使用反射的方式，成本较高，性能较低
    //直接使用集合的方式遍历，这里接受数据改为Map集合
    public Result findByPage(@PathVariable Integer page,
                             @PathVariable Integer size,
                             @RequestBody Map<String, Object> map) {
        //根据条件分页查询
        Page<Article> pageData = articleService.findByPage(map, page, size);

        //封装分页返回对象
        PageResult<Article> pageResult = new PageResult<>(
                pageData.getTotal(), pageData.getRecords()
        );

        //返回数据
        return new Result(true, StatusCode.OK, "分页查询成功", pageResult);

    }

}
