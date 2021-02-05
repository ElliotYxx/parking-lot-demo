package com.sheva.parkinglotdemo.repository;

import com.sheva.parkinglotdemo.domain.entity.Menu;
import com.sheva.parkinglotdemo.domain.entity.jpa.MenuResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author sheva
 * @create 2021/2/4 18:36
 */
public interface MenuRepository extends JpaRepository<Menu, Long> {

//    /** 查询所有菜单
//     * @return 结果菜单集合
//     **/
//    @Query(value = "select new com.sheva.parkinglotdemo.domain.entity.jpa.MenuResult(m.menuId, m.parentId, m.menuName. m.url, m.menuType, m.orderNum, m.createTime)" +
//            "from Menu m order by m.parentId, m.orderNum")
//    List<MenuResult> selectAllMenus();

}
