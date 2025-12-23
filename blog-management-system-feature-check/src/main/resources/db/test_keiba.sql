-- CREATE TABLE keiba(
-- 	"id" SERIAL PRIMARY KEY,
-- 	"馬名" VARCHAR(20) NOT NULL,
-- 	"日付" DATE NOT NULL,
-- 	"勝ち馬" VARCHAR(9),
-- 	"備考" VARCHAR(20)
-- )

-- ALTER TABLE keiba
-- RENAME COLUMN 馬名 TO レース名;
-- SELECT * FROM keiba;

-- INSERT INTO keiba("id","レース名","日付","勝ち馬","備考")
-- VALUES(DEFAULT,'桜花賞','2025/4/13','エンブロイダリー',''),(DEFAULT,'皐月賞','2025/4/20','ミュージアムマイル',''),
-- (DEFAULT,'オークス','2025/5/25','カムニャック',''),(DEFAULT,'日本ダービー','2025/6/1','クロワデュノール','北村友一ダービー初勝利'),
-- (DEFAULT,'秋華賞','2025/10/19','エンブロイダリー','牝馬クラシック2冠'),(DEFAULT,'菊花賞','2025/10/26','','')
SELECT * FROM keiba;