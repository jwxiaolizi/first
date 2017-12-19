package cn.itcast.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.dao.ItemsMapper;
import cn.itcast.pojo.Items;
import cn.itcast.pojo.ItemsExample;
import cn.itcast.pojo.ItemsExample.Criteria;
@Service
public class ItemService {
	@Autowired
	ItemsMapper itemMapper;
	
	public List<Items> findAllItems() {
		
		/*ItemsExample example = new ItemsExample();
		example.setDistinct(true);
		example.setOrderByClause("id");
		Criteria criteria = example.createCriteria();
		itemMapper.selectByExample(example);*/
		List<Items> list = itemMapper.selectByExampleWithBLOBs(null);
		return list;
	}
	public Items findItemsById(Integer id) {
		
		Items items = itemMapper.selectByPrimaryKey(id);
		
		return items;
		
	}
	public void updateItems(Items item) {
		itemMapper.updateByPrimaryKeySelective(item);
	}
	public void deleteById(Integer id){
		itemMapper.deleteByPrimaryKey(id);
	}
}
