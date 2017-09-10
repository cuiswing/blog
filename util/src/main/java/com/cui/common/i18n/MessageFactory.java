package com.cui.common.i18n;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * message对象工厂，带有设计模式的命名要见名知意，从别处拷过来的
 * <p>Description: <code>MessageFactory</code> is used to create {@link Message} instance.
 * Subclass should extends this class to customize the bundle reload control.
 * Implementations must save the instance in a field for stateful reload control.
 * Return null to fallback to default JVM behavior (permanent cache).
 * <p>
 * Created by cuishixiang on 2017-09-10.
 */
public class MessageFactory {
    private static final Logger logger = LoggerFactory.getLogger(MessageFactory.class);

    /**
     * This message key is used for {@link Message} instances that are not read from a
     * resource bundles but are created only with a string.
     */
    private static final String STATIC_MESSAGE_KEY = "-1";

    private static final transient Object[] EMPTY_ARGS = new Object[]{};

    protected ResourceBundle.Control reloadControl = null;

    protected MessageFactory() {
    }


    /**
     * Factory method to create a new {@link Message} instance that is filled with the formatted
     * message with id <code>messageKey</code> from the resource bundle <code>bundlePath</code>.
     *
     * @param bundlePath complete path to the resource bundle for lookup
     * @param messageKey message key
     *                   i.e. a.b.c.description=This is a sample message from {0} and {1}
     *                   {0}, {1} are the arguments placeholder
     * @param arguments  real argument replace the arguments placeholder
     */
    protected Message createMessage(String bundlePath, String messageKey, Object... arguments) {
        String messageString = getString(bundlePath, messageKey, arguments);
        return new Message(messageString, messageKey, arguments);
    }

    /**
     * Factory method to create a {@link Message} instance that is not read from a resource bundle.
     *
     * @param message Message's message text
     * @return a Messsage instance that has a message key of -1 and no arguments.
     */
    public static Message createStaticMessage(String message) {
        return new Message(message, STATIC_MESSAGE_KEY, EMPTY_ARGS);
    }

    /**
     * Factory method to read the message with id <code>messageKey</code> from the resource bundle.
     *
     * @param bundlePath complete path to the resource bundle for lookup
     * @param messageKey message key
     *                   i.e. a.b.c.description=This is a sample message from {0} and {1}
     *                   {0}, {1} are the arguments placeholder
     * @param args
     * @return formatted message as {@link String}
     */
    protected String getString(String bundlePath, String messageKey, Object[] args) {
        // We will throw a MissingResourceException if the bundle name is invalid
        // This happens if the code references a bundle name that just doesn't exist
        ResourceBundle bundle = getBundle(bundlePath);
        String message = bundle.getString(messageKey);
        return MessageFormat.format(message, args);
    }

    private ResourceBundle getBundle(String bundlePath) {
        Locale locale = Locale.getDefault();
        if (logger.isDebugEnabled()) {
            logger.debug("Loading resource bundle: " + bundlePath + " for locale " + locale);
        }
        final ResourceBundle.Control control = getReloadControl();
        return control != null ? ResourceBundle.getBundle(bundlePath, locale, getClassLoader(), control)
                : ResourceBundle.getBundle(bundlePath, locale, getClassLoader());
    }

    /**
     * Override this method to return the classloader for the bundle/module which
     * contains the needed resource files.
     */
    protected ClassLoader getClassLoader() {
        final ClassLoader ccl = Thread.currentThread().getContextClassLoader();
        // if there's a deployment classloader present, use it for finding resources
        return ccl == null ? getClass().getClassLoader() : ccl;
    }

    /**
     * Subclasses should override to customize the bundle reload control. Implementations must
     * save the instance in a field for stateful reload control. Return null to fallback to
     * default JVM behavior (permanent cache).
     */
    protected ResourceBundle.Control getReloadControl() {
        return reloadControl;
    }

}
