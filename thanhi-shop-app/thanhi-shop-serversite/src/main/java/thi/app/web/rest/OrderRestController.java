package thi.app.web.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import thi.app.model.dto.OrderDTO;
import thi.app.model.dto.OrderViewDTO;
import thi.app.service.IOrderService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/order")
public class OrderRestController {
    @Autowired
    IOrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDTO> create(@RequestBody OrderDTO ClientOrder) {
        OrderDTO orderDTO = orderService.create(ClientOrder);
        return ResponseEntity.ok().body(orderDTO);
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<OrderViewDTO>> getOrderViewByUsername(@PathVariable String username) {
        if (username.isEmpty()) {
            throw new UsernameNotFoundException("No username!!!");
        } else {
            List<OrderViewDTO> orderViewDTOs = orderService.getOrderViewByUsername(username);
            return ResponseEntity.ok().body(orderViewDTOs);
        }
    }
}
