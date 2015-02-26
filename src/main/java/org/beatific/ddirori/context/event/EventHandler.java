package org.beatific.ddirori.context.event;

public interface EventHandler<T extends ApplicationEvent> {

	public void handle(ApplicationEvent event);
}
