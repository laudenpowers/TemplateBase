package br.com.templatebase.view.util;

import java.util.ResourceBundle;

public class BundleUtils {

    public String getDescricaoEnum(Enum<?> enumObject) {
        ResourceBundle labels = ResourceBundle.getBundle(
        		"br.com.templatebase.view.bundle.BundleEnum");
        return labels.getString("enum.".concat(enumObject.toString()));
    }

}
