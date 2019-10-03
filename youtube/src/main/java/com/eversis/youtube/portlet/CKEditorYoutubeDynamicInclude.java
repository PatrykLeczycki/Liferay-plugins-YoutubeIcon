package com.eversis.youtube.portlet;

import com.liferay.portal.kernel.servlet.taglib.BaseDynamicInclude;
import com.liferay.portal.kernel.servlet.taglib.DynamicInclude;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author pleczycki
 */

@Component(immediate = true, service = DynamicInclude.class)
public class CKEditorYoutubeDynamicInclude extends BaseDynamicInclude {

    Logger logger = LoggerFactory.getLogger(CKEditorYoutubeDynamicInclude.class.getName());

    @Override
    public void include(
            HttpServletRequest request, HttpServletResponse response,
            String key)
            throws IOException {

        logger.error("include yt editor error");

        ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
                WebKeys.THEME_DISPLAY);

        PrintWriter printWriter = response.getWriter();

        StringBundler sb = new StringBundler(7);

        sb.append("<script src=\"");
        sb.append(themeDisplay.getPortalURL());
        sb.append(PortalUtil.getPathProxy());
        sb.append(_servletContext.getContextPath());
        sb.append("/ckeditor/plugins/youtube/plugin.js");
        sb.append("\" ");
        sb.append("type=\"text/javascript\"></script>");

        logger.error("sb = " + sb.toString());

        printWriter.println(sb.toString());
    }

    @Override
    public void register(DynamicIncludeRegistry dynamicIncludeRegistry) {
        dynamicIncludeRegistry.register(
                "com.liferay.frontend.editor.ckeditor.web#ckeditor#additionalResources");
        logger.error("register yt editor error");
    }

    @Reference(
            target = "(osgi.web.symbolicname=com.eversis.youtube)",
            unbind = "-"
    )
    protected void setServletContext(ServletContext servletContext) {
        _servletContext = servletContext;
    }

    private ServletContext _servletContext;

}