package fr.wynalia.generics.templates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Template {
    private String template;
    private final Map<String, String> variables = new HashMap<>();

    public Template(String template) {
        this.template = template;
    }

    public void set(String variable, String value) {
        if (variables.containsKey(variable)) variables.replace(variable, value);
        else variables.put(variable, value);
    }

    public String get(String variable) {
        return variables.get(variable);
    }

    public boolean isSet(String variable) {
        return variables.containsKey(variable);
    }

    public String render() {
        String[] $words = splitTemplate();
        for (int i = 0; i < $words.length; i++) {
            if ($words[i].startsWith("{%") && $words[i].endsWith("%}")) {
                String variable = $words[i].replace("{%", "").replace("%}", "");

                if (isSet(variable)) $words[i] = get(variable);
            }
        }
        return String.join("", $words);
    }

    private String[] splitTemplate() {
        List<String> parts = new ArrayList<>();

        int startIndex = 0;
        int endIndex;

        while ((startIndex = this.template.indexOf("{%", startIndex)) != -1) {
            endIndex = this.template.indexOf("%}", startIndex) + 2;
            parts.add(this.template.substring(0, startIndex));
            parts.add(this.template.substring(startIndex, endIndex));
            template = template.substring(endIndex);
            startIndex = 0;
        }

        if (!this.template.isEmpty()) {
            parts.add(this.template);
        }

        for (int i = 0; i < parts.size(); i++) {
            String part = parts.get(i);

            if (part.startsWith("{%") && part.endsWith("%}")) {
                parts.set(i, part.replaceAll(" ", ""));
            }
        }

        return parts.toArray(new String[0]);
    }
}
