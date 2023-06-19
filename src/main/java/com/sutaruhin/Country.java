package com.sutaruhin;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="country")
public class Country {
    @Id
    private String code;
    private String name;
    private int population;
}

/*Lombokのアノテーション
@Data：「getter/setter、toString、hashCode、equals」のメソッドを生成します。
@AllArgsConstructor：すべてのフィールドを引数に持つコンストラクタを生成します。
@NoArgsConstructor：引数を持たないコンストラクタを生成します。
Spring JPAのアノテーション
@Entity：エンティティクラス（データベースのテーブルにマッピングするクラス）であることを示します。
@Table：対応するテーブル名を指定します。
@Id：主キーであることを示します。
 */