package cn.itcast.action;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.itcast.action.exception.MyException;
import cn.itcast.pojo.Items;
import cn.itcast.pojo.QueryVo;
import cn.itcast.service.ItemService;

@Controller
@RequestMapping("/item")
public class itemAction {
	@Autowired
	ItemService itemService;
//	@RequestMapping(value="/itemList",method=RequestMethod.GET)
	@RequestMapping(value="/itemList")
	public String findAllItems(HttpServletRequest request,Model model) {
		
		List<Items> list = itemService.findAllItems();
		
//		request.setAttribute("itemsList", list);
//		int a = 10/0;
		model.addAttribute("itemsList", list);
		
		return "itemList";
	}
	
	
	@RequestMapping("/itemEdit")
	public ModelAndView update( Integer id,String name,Model model) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		
		Items items = itemService.findItemsById(id);
		
//		items = null;
		
		if(items == null){
			throw new MyException("您购买的商品已经下架....");
		}
		
		
		modelAndView.addObject("item", items);
		
		modelAndView.setViewName("editItem");
		
		return modelAndView;
		
	}
	@RequestMapping("/updateitem")
	public String updateitem(Items item,Model model,HttpServletRequest request,MultipartFile pictureFile) throws IllegalStateException, IOException {
		
		String oldfilename = pictureFile.getOriginalFilename();
		
		String newfilename = UUID.randomUUID().toString()+oldfilename.substring(oldfilename.lastIndexOf("."));
		
		pictureFile.transferTo(new File("e:\\ssm\\myimageserver\\"+newfilename));
		
		item.setPic(newfilename);
		
		itemService.updateItems(item);
		
//		model.addAttribute("id", item.getId());
		
		return "forward:/item/itemList.action";
		
	}
	@RequestMapping("/queryitem")
	public String queryitem(QueryVo vo) {
		
		System.out.println(vo);
		
		return "redirect:/item/itemList.action";
	}
	@RequestMapping("/deleteitems")
	public String deleteitems(Integer[] ids) {
		for (Integer id : ids) {
			itemService.deleteById(id);
		}
		
		return "redirect:/item/itemList.action";
		
	}
	@RequestMapping("/updateitems")
	public String updateitems(QueryVo vo) {
		
		List<Items> itemsList = vo.getItemList();
		
		for (Items items : itemsList) {
			itemService.updateItems(items);
			System.out.println(items);
			
		}

		
		return "redirect:/item/itemList.action";
		
	}
	@RequestMapping("/voidTest")
	public void voidTest(HttpServletResponse response,HttpServletRequest request) throws IOException, ServletException{
//		response.setCharacterEncoding("UTF-8");
//		response.getWriter().print("274技术好牛逼");
		response.sendRedirect(request.getContextPath()+"/item/itemList.action");
//		request.getRequestDispatcher("itemList.action").forward(request, response);
		
	}
	@RequestMapping("/jsonTest")
	public @ResponseBody Items jsonTest(@RequestBody Items items) {
		System.out.println(items);
		
		return items;
	}
	
}
