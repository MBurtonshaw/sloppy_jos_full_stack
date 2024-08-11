package com.techelevator.dao;

import com.techelevator.model.Item;
import com.techelevator.model.Side;
import com.techelevator.model.Topping;

import java.util.List;

public interface SideDao {
    Side getSide(int id);

    List<Side> getSides();
}
