package com.sutaruhin;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, String> {

}

/*リポジトリは JpaRepository インタフェースを継承したインタフェースとして用意します。
 * JpaRepository インタフェースには、あらかじめ以下ようなメソッドが提供されているため、上記だけで基本的なCRUD操作を行なうことが可能です。

findAll(): 全件検索
findById(): 1件検索（主キーによる検索）
save(): 作成、更新
deleteById(): 1件削除（主キーによる削除）
本来、リポジトリクラスには @Repository アノテーションを付ける必要がありますが、 JpaRepository インタフェースを継承した場合には省略可能です。*/