package com.bookshop01.main;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bookshop01.common.base.BaseController;
import com.bookshop01.goods.service.GoodsService;
import com.bookshop01.goods.vo.GoodsVO;

/* 
 * @EnableAspectJAutoProxy는 스프링 프레임워크에서 AOP를 적용하는 방법 중 하나입니다. 이 어노테이션을 사용하면 Spring AOP를 사용할 수 있으며, 프록시 기반 AOP를 지원하는 데 사용됩니다.
@EnableAspectJAutoProxy 어노테이션은 다음과 같은 역할을 수행합니다.
AspectJ 프록시를 활성화: @EnableAspectJAutoProxy를 사용하면 AspectJ 프록시를 활성화 할 수 있습니다. 이 프록시는 Spring AOP에서 사용되며, 메소드 호출 등의 조인 포인트에서 AOP를 적용할 수 있습니다.
AOP Proxy Bean 생성: @EnableAspectJAutoProxy를 사용하면 Spring이 AOP 프록시 빈을 생성합니다. 이 빈은 애스펙트를 사용하여 빈 메소드 호출을 프록시화합니다. 이를 통해 횡단 관심사(cross-cutting concern)를 쉽게 구현할 수 있습니다.
어드바이스(Advice) 적용: @EnableAspectJAutoProxy는 AOP 프록시 빈을 생성하고, 이를 통해 애스펙트를 적용합니다. 애스펙트는 어드바이스(Advice)를 포함하며, 어드바이스는 조인 포인트에 적용되는 코드를 정의합니다.
프록시 객체 생성: @EnableAspectJAutoProxy는 Spring이 프록시 객체를 생성하고, 원본 객체와 대체하는 데 사용됩니다. 이를 통해 AOP 프록시를 생성하고, 프록시 객체를 사용하여 메소드 호출 등의 조인 포인트에서 애스펙트를 적용할 수 있습니다.
 * 
 * 
 * */
@Controller("mainController")
@EnableAspectJAutoProxy
public class MainController extends BaseController {
	@Autowired
	private GoodsService goodsService;

	@RequestMapping(value = "/main/main.do", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session;
		ModelAndView mav = new ModelAndView();
		String viewName = (String) request.getAttribute("viewName");
		mav.setViewName(viewName);

		session = request.getSession();
		session.setAttribute("side_menu", "user");
		Map<String, List<GoodsVO>> goodsMap = goodsService.listGoods();
		mav.addObject("goodsMap", goodsMap);
		return mav;
	}
}
