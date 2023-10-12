import requests

# POST 요청을 보낼 URL 설정
url = 'http://192.168.0.56:5001'  # 서버의 URL을 수정하세요

# 이미지 파일을 열고 POST 데이터로 전송
with open('galbi2.jpg', 'rb') as image_file:  # 이미지 파일의 경로를 수정하세요
    files = {'image': ('glabi2.jpg', image_file, 'image/jpeg')}
    response = requests.post(url, files=files)

# 서버에서 받은 응답 출력
print(response.text)
