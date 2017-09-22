#include <jni.h>
#include <stdio.h>
#include <stdlib.h>
#include <malloc.h>
#include <string.h>

extern "C"
jstring
Java_com_my_mywebview_activity_JniTestActivity_fromJniMsg(JNIEnv *env,jobject obj, jstring str){
    const char *question = env->GetStringUTFChars(str, JNI_FALSE);
    char *answer = "我是来自JNI!!!**结束**";
    char *data = (char *) malloc(strlen(question) + strlen(answer)+1);
    strcpy(data,question);
    strcat(data, "**我是JNI的中间过渡拼接**");
    strcat(data, answer);
    return env->NewStringUTF(data);
}