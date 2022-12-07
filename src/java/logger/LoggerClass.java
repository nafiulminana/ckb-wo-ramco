/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logger;

import javax.xml.crypto.dsig.spec.ExcC14NParameterSpec;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Amaran Sidhiq
 */
public class LoggerClass {

    private static final Log log = LogFactory.getLog(LoggerClass.class);

    public static void logError(Class theClass, Throwable throwable) {
        log.error(theClass, throwable);
    }

    public static void logError(Throwable throwable) {
        log.error(throwable);
    }

    public static void logDebug(Class theClass, Throwable throwable) {
        if(log.isDebugEnabled()){
            log.debug(theClass, throwable);
        }
    }

    public static void logDebug(Throwable throwable) {
        if (log.isDebugEnabled()) {
            log.debug(throwable);
        }
    }

    public static void main(String[] args) {
        logError(new Exception("aglasuklgauksghaksgh"));
    }
}
