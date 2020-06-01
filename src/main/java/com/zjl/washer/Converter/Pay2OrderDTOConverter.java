package com.zjl.washer.Converter;

import com.zjl.washer.DAO.Pay;
import com.zjl.washer.DTO.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class Pay2OrderDTOConverter {

    public static OrderDTO convert(Pay pay){
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(pay, orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convert(List<Pay> payList){
        return payList.stream().map(e ->
                convert(e)
        ).collect(Collectors.toList());
    }

}
