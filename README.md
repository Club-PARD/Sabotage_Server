<p align="center">
    <img src="./assets/exit-banner.png" />
    <img src="./assets/exit-logo.png" />
</p>


# `EXIT`/server (구 `Sabotage`)
“스마트폰 이용 시간을 유익하게 변화시킬 수 있는 스크린타임 서비스” - `EXIT`의 서버 쪽 `repository`

## 주요 기능
<table>
    <tr>
        <td align="center"><img src="./assets/action_item.png" width="50%" /></td>
        <td align="center"><img src="./assets/goal_group.png" width="50%" /></td>
        <td align="center"><img src="./assets/analysis.png" width="50%" /> <br></td>
    </tr>
    <tr>
        <td align="center">목표 습관 작성</td>
        <td align="center">제한 그룹 작성</td>
        <td align="center">스크린타임 시간 분석</td>
    </tr>
</table><br>

---
---

# 코드 외부

## 서버 개발 구성원
| 유채우 |
| :---: |
| ![](./assets/not-found.png) |
| [turbstructor](https://github.com/turbstructor) |

## 개발 환경
<img src="https://img.shields.io/badge/Java-437291?style=for-the-badge&logo=OpenJDK&logoColor=white"> <img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> <img src="https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white"> <img src="https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=white"> <br>
<img src="https://img.shields.io/badge/MariaDB-003545?style=for-the-badge&logo=mariadb&logoColor=white"> <img src="https://img.shields.io/badge/Archlinux-1793D1?style=for-the-badge&logo=archlinux&logoColor=white"> <br>
<img src="https://img.shields.io/badge/Visual Studio Code-007ACC?style=for-the-badge&logo=visual studio code&logoColor=white"> <img src="https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white"> <br>
<img src="https://img.shields.io/badge/Notion-000000?style=for-the-badge&logo=notion&logoColor=white"> <img src="https://img.shields.io/badge/Figma-F24E1E?style=for-the-badge&logo=figma&logoColor=white"> <img src="https://img.shields.io/badge/Mermaid-FF3670?style=for-the-badge&logo=mermaid&logoColor=white"> <img src="https://img.shields.io/badge/Obsidian-7C3AED?style=for-the-badge&logo=obsidian&logoColor=white">

| 구분 | 사용한 환경 |
| --- | --- |
| IDE | `Visual Studio Code` |
| Build Tool / Version | `Gradle` 8.5 |
| `java -version` | 17.0.9 |
| `Spring Boot` Version | 3.2.1 |
| Database | `MariaDB` 11.2.2 |
| API Test | `Swagger`, `Postman` |
| 서버 배포 | 개인 컴퓨터(`Archlinux` w/ `linux-zen`) |

---

# 코드 내부

## ERD
![](./assets/sabotage_server_erd.png)

## API Docs
![](./assets/Sabotage_Server_API_Docs.png)

## Package Structure
계층 중심 패키지 구조
```
main
├── java
│   └── club
│       └── pard
│           └── server
│               └── soonjji
│                   └── sabotage
│                       ├── configuration
│                       ├── controller
│                       │   ├── actionitem
│                       │   ├── ejection
│                       │   ├── goalgroup
│                       │   ├── phoneusage
│                       │   └── user
│                       ├── converter
│                       ├── dto
│                       │   ├── request
│                       │   │   ├── actionitem
│                       │   │   ├── appusage
│                       │   │   ├── goalgroup
│                       │   │   ├── phoneusage
│                       │   │   └── user
│                       │   └── response
│                       │       ├── actionitem
│                       │       ├── ejection
│                       │       ├── goalgroup
│                       │       ├── phoneusage
│                       │       └── user
│                       ├── entity
│                       │   ├── actionitem
│                       │   ├── ejection
│                       │   ├── goalgroup
│                       │   ├── phoneusage
│                       │   └── user
│                       ├── exception
│                       ├── repository
│                       │   ├── actionitem
│                       │   ├── ejection
│                       │   ├── goalgroup
│                       │   ├── phoneusage
│                       │   └── user
│                       └── service
│                           ├── actionitem
│                           ├── ejection
│                           ├── goalgroup
│                           ├── phoneusage
│                           └── user
```

## Misc
### (시도한) Branch Strategy
`git-flow` branch strategy (reference from [git-flow cheatsheet](https://danielkummer.github.io/git-flow-cheatsheet/))

### (시도한) Commit Convention
[Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/#summary)와 [Angular / Commit Message Guidelines](https://github.com/angular/angular/blob/22b96b9/CONTRIBUTING.md#-commit-message-guidelines)를 참고하여 commit을 시도하였습니다.