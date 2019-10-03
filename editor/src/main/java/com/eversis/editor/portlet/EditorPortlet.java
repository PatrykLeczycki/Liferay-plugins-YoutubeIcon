package com.eversis.editor.portlet;

import com.eversis.editor.constants.EditorPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import org.osgi.service.component.annotations.Component;

import java.io.IOException;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author patry
 */
@Component(
        immediate = true,
        property = {
                "com.liferay.portlet.display-category=category.sample",
                "com.liferay.portlet.header-portlet-css=/css/main.css",
                "com.liferay.portlet.instanceable=true",
                "javax.portlet.display-name=Editor",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=/view.jsp",
                "javax.portlet.name=" + EditorPortletKeys.EDITOR,
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=power-user,user"
        },
        service = Portlet.class
)
public class EditorPortlet extends MVCPortlet {

    @Override
    public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
            throws IOException, PortletException {

        super.doView(renderRequest, renderResponse);
    }
}