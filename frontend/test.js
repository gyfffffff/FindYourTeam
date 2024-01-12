const request = require('request')
const AK = "MRPKgdCEzyvfNtqQWqQUPjgc"
const SK = "denRkDRoGLSDeQMBOBthtbharjqGOCj8"

async function main() {
    var options = {
        'method': 'POST',
        'url': 'https://aip.baidubce.com/rpc/2.0/ai_custom/v1/wenxinworkshop/plugin/fupvnjquq2qj28ba/?access_token=' + await getAccessToken(),
        'headers': {
                'Content-Type': 'application/json'
        },
        body: JSON.stringify({
                "query": "2023年华师大校内数模比赛时间",
                "plugins": [
                        "uuid-zhishiku"
                ],
                "verbose": false
        })

    };

    request(options, function (error, response) {
        if (error) throw new Error(error);
        console.log(response.body);
    });
}

/**
 * 使用 AK，SK 生成鉴权签名（Access Token）
 * @return string 鉴权签名信息（Access Token）
 */
function getAccessToken() {

    let options = {
        'method': 'POST',
        'url': 'https://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials&client_id=' + AK + '&client_secret=' + SK,
    }
    return new Promise((resolve, reject) => {
        request(options, (error, response) => {
            if (error) { reject(error) }
            else { resolve(JSON.parse(response.body).access_token) }
        })
    })
}
main();
