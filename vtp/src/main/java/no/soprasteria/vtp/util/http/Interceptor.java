package no.soprasteria.vtp.util.http;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import no.soprasteria.vtp.util.config.KontrollerKonfig;

@Component
public class Interceptor implements HandlerInterceptor {

    private static final Logger LOG = LoggerFactory.getLogger(Interceptor.class);

    private final KontrollerKonfig kontrolleKonfig;

    @Autowired
    public Interceptor(KontrollerKonfig kontrolleKonfig) {
        this.kontrolleKonfig = kontrolleKonfig;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        var requestURI = request.getRequestURI();
        if (requestURI.contains("api/testdata") || requestURI.contains("api/teknisk")) {
            return true;
        }
        if (kontrolleKonfig.delaySkatteetaten() > 0 || kontrolleKonfig.delayVegvesen() > 0) {
            LOG.info("Det er mottatt request mot {}", request.getRequestURI());
            LOG.warn("Det er lagt på {} sekunder delay. Venter...", kontrolleKonfig.delaySkatteetaten());
            TimeUnit.SECONDS.sleep(kontrolleKonfig.delaySkatteetaten());
            LOG.warn("Ferdig å vente.");
        }
        return true;
    }

}
