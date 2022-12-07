package thi.app.web.rest.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import thi.app.model.dto.OrderDTO;
import thi.app.service.IOrderService;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
    @RequestMapping("/admin/order")
public class OrderManagent {

    @Autowired
    IOrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrder(@RequestParam(value = "status", defaultValue = "0") Optional<String> status){
        List<OrderDTO> list = null;
        if (status.get().equals("ALL")){
            list = orderService.getAll();
        } else {
            list = orderService.getOrdersByStatus(status.get());
        }
        return ResponseEntity.ok().body(list);
    }

    @PutMapping
    public ResponseEntity<OrderDTO> update(@RequestBody OrderDTO orderDTO){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        orderDTO.setModifiedBy(username);
        orderDTO.setModifiedDate(Instant.now());
        OrderDTO newOrderDTO = orderService.update(orderDTO);

        return ResponseEntity.ok().body(newOrderDTO);
    }
}
