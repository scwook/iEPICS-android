//
// Created by scwook on 5/5/18.
//

#ifndef IEPICS_ANDROID_CHANNELACCESSCLIENT_H
#define IEPICS_ANDROID_CHANNELACCESSCLIENT_H

#include <map>
#include <string>
#include "cadef.h"

using namespace::std;

static const unsigned short max_pv = 100;
static void connectionCallback( struct connection_handler_args cha );
static void instantConnectionCallback(struct connection_handler_args cha);

static void eventCallbackString( evargs eha );
static void eventCallbackShort( evargs eha );
static void eventCallbackFloat( evargs eha );
static void eventCallbackEnum( evargs eha );
static void eventCallbackLong( evargs eha );
static void eventCallbackDouble( evargs eha );

typedef struct {
    chid    chid;
    evid    evid;
} CANODE;

class ChannelAccessClient {

public:
    CANODE *myCAnode[max_pv];
    CANODE *instantCAnode;

    map<int, string>pvMap;
    map<int, string>pvMapIndex;

public:
    ChannelAccessClient(void);
    ~ChannelAccessClient(void);

    bool test(int value);

private:

};


#endif //IEPICS_ANDROID_CHANNELACCESSCLIENT_H
