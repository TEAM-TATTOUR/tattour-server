<img src="https://github.com/TEAM-TATTOUR/tattour-server/assets/81281190/5753b247-7ec9-4929-b318-432785a19c88, " height="600">

# tattour

### 후회없는 선택의 여정을 함께, 커스텀 타투 체험 서비스 TATTOUR

##  🫶 Tattour Server Developers

<img src="" width="200"> | <img src="" width="200"> |
:---------:|:----------:|
유서린 | 이찬미 |
[SeorinY](https://github.com/SeorinY) | [05AM](https://github.com/05AM) |
CI/CD, 스티커,커스텀, 테마, 스타일| 소셜 로그인, 전화번호 인증, 회원, 배송지, 포인트 |
<br>

### 아키텍처 구조

```
├── 📂 domain
│   ├── 📂 sticker
│   │    ├── 📂 controller
│   │    │    └── 📂 dto
│   │    │         └── 📂 request
│   │    │         └── 📂 response
│   │    ├── 📂 domain
│   │    ├── 📂 exception
│   │    ├── 📂 repository
│   │    │   └── 📂 impl
│   │    └── 📂 service
│   │        └── 📂 dto
│   │            ├── 📂 request
│   │            └── 📂 response
│   │
│   ├── 📂 banner
│   ├── 📂 banner
│   ├── 📂 custom
│   ├── 📂 discount
│   ├── 📂 order
│   ├── 📂 point
│   ├── 📂 search
│   ├── 📂 style
│   └── 📂 theme
├── 📂 global
│   ├── 📂 config
│   ├── 📂 dto
│   ├── 📂 exception
│   └── 📂 util
└── 📂 infra
    ├── 📂 s3
    ├── 📂 sms
    └── 📂 socialLogin
```

### 사용 스택
![image](https://github.com/TEAM-TATTOUR/tattour-server/assets/81281190/394556c9-c660-43e3-8645-a0ee0a122157)