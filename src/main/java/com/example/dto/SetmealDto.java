package com.example.dto;

import com.example.entity.Setmeal;
import com.example.entity.SetmealDish;
import lombok.Data;
import java.util.List;

/**
 * @author yanyu
 * @datetime 2023/3/7 0:06
 */
@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
