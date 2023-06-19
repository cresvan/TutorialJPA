package com.sutaruhin;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service はサービスクラスであることを示します。これらのアノテーションを付けたクラスはSpringを起動した際にDIコンテナーへ登録されます。
@Service
public class CountryService {
	@Autowired
	private CountryRepository repository;


	public List<Country> getCountryList() {

		return repository.findAll();
	}

	public Country getCountry(String code) {

		Optional<Country> option = repository.findById(code);

		Country country = option.orElse(null);
		return country;
	}


	/*更新（追加、更新、削除）を行なうメソッドには @Transactional アノテーションを付けるようにします。
	 * @Transactional アノテーションはこのクラスのすべてのメソッドをトランザクション管理対象に設定します。
	 * トランザクションとは一連のデータベース操作が失敗した場合、操作前の状態に巻き戻すことでデータベースの整合性を担保する仕組みです。*/
	@Transactional
	public void updateCountry(String code, String name, int population) {
		Country country = new Country(code, name, population);
		repository.save(country);
	}


	@Transactional
	public void deleteCountry(String code) {
		repository.deleteById(code);
	}
}

/*コンポーネントを利用する際は @Autowired アノテーションを付けることで、DIコンテナーを経由してインスタンスを取得するようになります。
 * repository変数に @Autowired を付けることで、DIによるコンポーネントの使用を定義しています。*/
