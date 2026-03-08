package com.example;

import com.aliucord.entities.Plugin;
import com.aliucord.patcher.Hook;
import com.aliucord.patcher.Patcher;

import com.discord.models.message.Message;

public class AdvancedPlugin extends Plugin {

    @Override
    public void start() throws Throwable {

        logger.info("Advanced Plugin Started");

        // Message hook
        Patcher.addPatch(
            Message.class.getDeclaredMethod("getContent"),
            new Hook(param -> {

                Object result = param.getResult();

                if (result instanceof String) {
                    String content = (String) result;

                    if (content.contains("hello")) {
                        logger.info("Detected hello message");
                    }
                }

                return null;
            })
        );

    }

    @Override
    public void stop() {

        Patcher.unpatchAll();
        logger.info("Advanced Plugin Stopped");

    }
}
