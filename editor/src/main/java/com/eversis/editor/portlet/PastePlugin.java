package com.eversis.editor.portlet;

import com.liferay.portal.kernel.servlet.taglib.BaseDynamicInclude;
import com.liferay.portal.kernel.servlet.taglib.DynamicInclude;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component(immediate = true, service = DynamicInclude.class)
public class PastePlugin extends BaseDynamicInclude {

    Logger logger = LoggerFactory.getLogger(PastePlugin.class);

    @Override
    public void include(
            HttpServletRequest request, HttpServletResponse response,
            String key)
            throws IOException {

        logger.error("include paste error");

        ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
                WebKeys.THEME_DISPLAY);
        StringBundler sb = new StringBundler(7);

        sb.append("<script src=\"");
        sb.append(themeDisplay.getPortalURL());
        sb.append(PortalUtil.getPathProxy());
        sb.append(_servletContext.getContextPath());
        sb.append("/ckeditor/plugins/youtube/plugin.js");
        sb.append("\" ");
        sb.append("type=\"text/javascript\"></script>");

        logger.error("sb = " + sb.toString());

        Bundle bundle = _bundleContext.getBundle();

        URL entryURL = bundle.getEntry(
                "/META-INF/resources/plugin.js");
        StreamUtil.transfer(
                entryURL.openStream(), response.getOutputStream(), false);
    }

    @Override
    public void register(DynamicIncludeRegistry dynamicIncludeRegistry) {
        dynamicIncludeRegistry.register(
                "com.liferay.frontend.editor.ckeditor.web#ckeditor#" +
                        "onEditorCreate");
        logger.error("register paste error");
    }

    @Activate
    protected void activate(BundleContext bundleContext) {
        _bundleContext = bundleContext;
    }
    private BundleContext _bundleContext;

    @Reference(
            target = "(osgi.web.symbolicname=com.eversis.youtube)",
            unbind = "-"
    )
    protected void setServletContext(ServletContext servletContext) {
        _servletContext = servletContext;
    }

    private ServletContext _servletContext;

}