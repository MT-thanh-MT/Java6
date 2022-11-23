package thi.app.web.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thi.app.model.dto.OrderDTO;
import thi.app.service.IOrderService;

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
}
