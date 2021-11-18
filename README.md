# Android 파트원 

## 프로젝트 폴더링
### 코틀린
- activity : 액티비티들을 모두 모아 둠
  - HomeActivity : 메인 화면
  - PostViewActivity : 게시물 보기 화면
  - PostWriteActivity : 게시물 쓰기 화면
- fragment : 프래그먼트들을 모두 모아 둠
  - BusinessCardFragment : 명함첩
  - CareerFragment : 커리어
  - CategoryDialogFragment : 글쓰기 항목 선택 시 나오는 다이얼로그
  - CommunityFragment : 커뮤니티
  - MypageFragment : 마이페이지
- adapter : Adapter와 관련한 모든 class 모음
  - HomeViewPagerAdapter : ViewPager2 작업을 위한 어댑터
  - BestPostAdapter : 커뮤니티 프래그먼트의 게시물(recyclerView)의 아이템을 변환하기 위한 어댑터
- util : Util과 관련한 class 모음
  - BestPostData : 게시물 recyclerView의 data class
</br>

### layout
- anim : 애니메이션 작업을 위한 디렉토리
- color : 색상 작업을 위한 디렉토리
- drawable : 도형이나 svg파일, png파일 작업을 위한 디렉토리
- layout : 실제 보이는 화면들
- menu : BottomNavigationView의 menu 설정을 위한 폴더
</br>

## Git
### git branch
- master : 최종 배포 작업물만 머지
- develop : 세부기능이 완벽하게 구현될때만 머지
- feature : 세부작업(액티비티, 프래그먼트 등 기준으로 나눔)
  - asset : 에셋 작업을 위한 브랜치
  - CommunityFragment : 커뮤니티 프래그먼트 작업을 위한 브랜치
  - HomeActivity : 홈 액티비티 작업을 위한 브랜치
  - initialSetting : 초기 작업환경 구축을 위한 브랜치
  - PostWriteActivity : 게시물 작성 액티비티를 위한 브랜치
</br>

### git commit 예시
맨 앞에 어떤 작업을 했는지 명시, 그 후에 파일명 명시, 마지막에 작업 내용 써주기
- [Add] HomeActivity BottomNavigationView
- [Delete] BestPostData data's post_num
- [Fix] BestPostAdapter item which index is 0~2 to gray3 color
- [Update] PostViewActivity setText main,sub category
- [UI] Change StatusBar Color Purple into White, Remove ActionBar, Add Font Style
</br>

## 코드 컨벤션
### 코틀린
- 변수명
  - lower camel case
  - 다빈이의 세미나 변수명대로 설정
</br>

### layout
- id
  - 뷰의 대문자 위주로 설정
    - tv_name
    - et_name
    - bnv
    - vp_home
- 뷰 네임
  - 한눈에 알아볼 수 있는 짧은 속성 단어 위주로 
