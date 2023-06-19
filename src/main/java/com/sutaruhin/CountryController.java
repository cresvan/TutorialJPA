package com.sutaruhin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//コントローラーでは、CountryService サービスを使い、Country クラスの配列を取得、Modelに登録しています。
@Controller
@RequestMapping("country")
public class CountryController {
	@Autowired
	private CountryService service;


	@GetMapping("/list")
	public String getList(Model model) {

		model.addAttribute("countrylist", service.getCountryList());

		return "country/list";
	}


	@GetMapping(value = { "/detail", "/detail/{code}/" })
	public String getCountry(@PathVariable(name = "code", required = false) String code, Model model) {

		Country country = code != null ? service.getCountry(code) : new Country();

		model.addAttribute("country", country);

		return "country/detail";
	}
	/*詳細画面は「新規登録」と「更新」の2つの画面を兼ねています。そこで @GetMapping(value = { "/detail", "/detail/{code}/" }) で2つのパスにマッピングしています。
	codeが無い場合（新規登録）: country/detail
	codeがある場合（更新）: country/detail/{code}
	codeが指定されていたら、サービスの getCountry() メソッドで1件検索して結果を取得します。
	codeが指定されていない場合は、空の Country オブジェクトを作成します。必ず Country オブジェクトをModelに登録することで、 detail.html テンプレートの方でエラーになることを防いでいます。*/



	@PostMapping("/detail")
	public String postCountry(@RequestParam("code") String code, @RequestParam("name") String name,
			@RequestParam("population") int population, Model model) {

		service.updateCountry(code, name, population);


		return "redirect:/country/list";
	}
	/*画面のformで入力した値をパラメータとして受け取り、サービスの updateCountry() メソッドで更新処理を行います。
	 * codeの値が同じである（＝すでに登録されている）レコードがない場合には追加されます。
	 * 更新処理が終わったら一覧画面にリダイレクトします。リダイレクトさせるには "redirect:テンプレート名" と記述します。*/


	@GetMapping("/delete")
	public String deleteCountryForm(Model model) {

		return "country/delete";
	}


	@PostMapping("/delete")
	public String deleteCountry(@RequestParam("code") String code, Model model) {

		service.deleteCountry(code);


		return "redirect:/country/list";
	}
}

/*@RequestMappingアノテーションは、URLのプレフィックス（アプリケーション名の前につける言葉）として利用されます。
 * 上記の場合、プレフィックスに「country」、 getList() メソッドに「/list」をマッピングしていることから、
 * http://localhost:8080/country/list にアクセスすると getList() メソッドが実行されます。
 * Due to line 11 and 22, 'country' folder needs to be created under src/main/resources/templates*/