package com.eversis.editor.portlet;

import com.liferay.portal.kernel.editor.configuration.BaseEditorConfigContributor;
import com.liferay.portal.kernel.editor.configuration.EditorConfigContributor;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import javax.servlet.ServletContext;

@Component(
        property = {"editor.name=ckeditor",
                "service.ranking:Integer=100"},
        service = EditorConfigContributor.class
)
public class YoutubeIcon extends BaseEditorConfigContributor {

    Logger logger = LoggerFactory.getLogger(YoutubeIcon.class);

    @Override
    public void populateConfigJSONObject(JSONObject jsonObject, Map<String, Object> inputEditorTaglibAttributes,
            ThemeDisplay themeDisplay, RequestBackedPortletURLFactory requestBackedPortletURLFactory) {

        logger.error("populate error");

        String extraPlugins = jsonObject.getString("extraPlugins");

        if(!extraPlugins.contains("youtube")){
            extraPlugins += ",youtube";
        }

        jsonObject.put("extraPlugins", extraPlugins);

        JSONArray toolbarsJSONArray = jsonObject.getJSONArray("toolbar_liferay");

        JSONArray yt = JSONFactoryUtil.createJSONArray();
        yt.put("Youtube");

        toolbarsJSONArray.put(yt);
        jsonObject.put("toolbar_liferay", toolbarsJSONArray);
    }
}