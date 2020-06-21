package com.aaron.resource.website.controller.web;

import com.aaron.resource.website.pojo.TbType;
import com.aaron.resource.website.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by huangtao on 17/1/7.
 */
@Controller
@RequestMapping("/type/")
public class TypeController {

    @Autowired
    private TypeService tbListTitleService;

    @RequestMapping("home")
    public String home(Model model){
        List<TbType> titleList = tbListTitleService.list();
        model.addAttribute("titleList", titleList);
        return "titleHome";
    }

    @RequestMapping("showSavaPage")
    public String showSavaPage(Model model, @RequestParam(defaultValue = "0") Integer id){

        if (id != 0){
            model.addAttribute("type", tbListTitleService.findById(id));
        }

        return "titleEdit";
    }

    @RequestMapping("save")
    public String saveTitle(@ModelAttribute TbType tbListTitle, Model model){
        try {

            if (tbListTitle.getId() == null){
                tbListTitleService.saveTitle(tbListTitle);
            } else {
                tbListTitleService.updateTitle(tbListTitle);
            }

            return "redirect:/type/home";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "插入或更新失败 "+e.getMessage()+"");
            return "titleEdit";
        }
    }

    @RequestMapping("deleteById")
    public String deleteById(Integer id){
        try {
            tbListTitleService.deleteById(id);
        } catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/type/home";
    }
}
