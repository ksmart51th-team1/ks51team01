package team01.yaksutor.pharmacy.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import team01.yaksutor.dto.ShoppingCart;
import team01.yaksutor.pharmacy.service.PhShoppingCartService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CartInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(CartInterceptor.class);
    private final PhShoppingCartService phShoppingCartService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String memberId = (String) request.getSession().getAttribute("S_ID");
        System.out.println(memberId);
        if (memberId != null) {
            List<ShoppingCart> cartItems = phShoppingCartService.getCartItemsByMemberId(memberId);
            int cartItemCount = (cartItems != null) ? cartItems.size() : 0;

            request.setAttribute("cartItemCount", cartItemCount);
            request.setAttribute("cartItems", cartItems);
        }
        String a = (String) request.getSession().getAttribute("S_LEVEL");
        log.info("a {}",a);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            String slevel = (String) request.getSession().getAttribute("S_LEVEL");
            boolean isAuthorizedLevel = "개국약사".equals(slevel) || "관리약사".equals(slevel);

            if (isAuthorizedLevel && request.getAttribute("cartItemCount") != null) {
                modelAndView.addObject("cartItemCount", request.getAttribute("cartItemCount"));
                modelAndView.addObject("cartItems", request.getAttribute("cartItems"));
            }
        }
    }
}


