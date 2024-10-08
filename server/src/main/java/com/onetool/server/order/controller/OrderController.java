package com.onetool.server.order.controller;

import com.onetool.server.global.auth.login.PrincipalDetails;
import com.onetool.server.global.exception.ApiResponse;
import com.onetool.server.order.dto.request.OrderRequest;
import com.onetool.server.order.service.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.onetool.server.order.dto.response.OrderResponse.*;


@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderServiceImpl orderService;

    //결제 페이지로 이동
    @GetMapping("/orders")
    public ApiResponse<OrderPageMemberResponseDto> getUserInformationForOrder(@AuthenticationPrincipal PrincipalDetails principal,
                                                                              @RequestBody OrderRequest request){
        return ApiResponse.onSuccess(orderService.getMemberInfoForOrder(principal.getContext(), request));
    }

    //결제 완료 시
    @PostMapping("/orders/complete")
    public ApiResponse<?> createOrder(@AuthenticationPrincipal PrincipalDetails principal,
                                      @RequestBody OrderRequest request) {
        return ApiResponse.onSuccess(orderService.createOrders(principal.getContext(), request));
    }
}