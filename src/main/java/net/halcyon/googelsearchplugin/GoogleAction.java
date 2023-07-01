package net.halcyon.googelsearchplugin;

import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class GoogleAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        Editor editor = event.getData(PlatformDataKeys.EDITOR);

        if (editor != null) {
            String selectedText = editor.getSelectionModel().getSelectedText();

            if (selectedText != null) {
                String encodedText = URLEncoder.encode(selectedText, StandardCharsets.UTF_8);

                String url = String.format("https://www.google.com/search?q=%s", encodedText);

                BrowserUtil.browse(url);
            } else {
                Messages.showMessageDialog("The selected text is empty, please try again", "Google Search Action", Messages.getInformationIcon());
            }
        }
    }
}
