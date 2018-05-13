#include <jni.h>
#include <string.h>
#include <android/log.h>

#include "ChannelAccessClient.h"

typedef struct {
    JNIEnv* env;
    jclass 	classID;
    jmethodID 	methodID;
} JniMethodInfo;

#define  LOG_TAG    "logText"
#define  LOGD(...)  __android_log_print(ANDROID_LOG_DEBUG,LOG_TAG,__VA_ARGS__)

static JavaVM* g_JVM;

jint JNI_OnLoad(JavaVM *jvm, void *reserved)
{
    LOGD("JNI_OnLoad");
    g_JVM = jvm;

    JNIEnv* env;
    if (jvm->GetEnv((void **)&env, JNI_VERSION_1_4) != JNI_OK) {
        LOGD("GETENVFAILEDONLOAD");
        return -1;
    }

    return JNI_VERSION_1_4;
}

JNIEnv* getJNIEnv(JavaVM* jvm)
{

    if (NULL == jvm) {
        LOGD("Failed to get JNIEnv. JniHelper::getJavaVM() is NULL");
        return NULL;
    }

    JNIEnv *env = NULL;
    // get jni environment
    jint ret = jvm->GetEnv((void**)&env, JNI_VERSION_1_4);

    switch (ret) {
        case JNI_OK :
            // Success!
            return env;

        case JNI_EDETACHED :
            // Thread not attached

            // TODO : If calling AttachCurrentThread() on a native thread
            // must call DetachCurrentThread() in future.
            // see: http://developer.android.com/guide/practices/design/jni.html

            if (jvm->AttachCurrentThread(&env, NULL) < 0)
            {
                LOGD("Failed to get the environment using AttachCurrentThread()");
                return NULL;
            } else {
                // Success : Attached and obtained JNIEnv!
                return env;
            }

        case JNI_EVERSION :
            // Cannot recover from this error
            LOGD("JNI interface version 1.4 not supported");
        default :
            LOGD("Failed to get the environment using GetEnv()");
            return NULL;
    }
}

jclass getClassID(JNIEnv *pEnv, const char* className)
{
    jclass ret = pEnv->FindClass(className);
    if (! ret)
    {
        LOGD("Failed to find class of %s", className);
    }

    return ret;
}

bool getStaticMethodInfo(JavaVM* jvm, JniMethodInfo &methodinfo, const char* className, const char *methodName, const char *paramCode)
{
    jmethodID methodID = 0;
    JNIEnv *pEnv = 0;
    bool bRet = false;

    do
    {
        pEnv = getJNIEnv(jvm);
        if (! pEnv)
        {
            break;
        }

        jclass classID = getClassID(pEnv, className);

        methodID = pEnv->GetStaticMethodID(classID, methodName, paramCode);
        if (! methodID)
        {
            LOGD("Failed to find static method id of %s", methodName);
            break;
        }

        methodinfo.classID = classID;
        methodinfo.env = pEnv;
        methodinfo.methodID = methodID;

        bRet = true;
    } while (0);

    return bRet;
}


void callback(JavaVM* jvm, const char* value){
    JniMethodInfo methodInfo;
    if (! getStaticMethodInfo(jvm, methodInfo, "com/example/scwook/iepics_android/ChannelAccessNotification", "testFunc", "(Ljava/lang/String;)V"))
    {
        return;
    }

    jstring stringArg = methodInfo.env->NewStringUTF(value);
    methodInfo.env->CallStaticVoidMethod(methodInfo.classID, methodInfo.methodID, stringArg);
    methodInfo.env->DeleteLocalRef(stringArg);
    methodInfo.env->DeleteLocalRef(methodInfo.classID);
}

extern "C"
JNIEXPORT jboolean

JNICALL
Java_com_example_scwook_iepics_1android_MenuActivity_test(JNIEnv *env, jobject /* this */) {
    callback(g_JVM, "call testCallback()");
    ChannelAccessClient *caObject = new ChannelAccessClient();

    return caObject->test(0);
    return true;
}