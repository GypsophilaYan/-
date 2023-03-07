package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.dto.SetmealDto;
import com.example.entity.Setmeal;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {
    /**
     * 新增套餐，同时需要保存套餐和菜品的关联关系
     * @author yanyu
     * @datetime 2023/3/6 22:59
     * @param setmealDto
     */
    void saveWithDish(SetmealDto setmealDto);

    /**
     * 删除套餐，同时需要删除套餐和菜品的关联关系
     * @author yanyu
     * @datetime 2023/3/6 23:02
     * @param ids
     */
    void removeWithDish(List<Long> ids);
}
