from flask import Flask, jsonify

app = Flask(__name__)

@app.route('/get-categories', methods=['GET'])
def get_categories():
    # ساخت داده JSON برای دسته‌بندی‌ها
    response = {
        "categories": [
            {
                "category": "کالای دیجیتال",
                "products": [
                    {
                        "title": "لپتاپ",
                        "price": 20000000.0,
                        "description": "لپتاپ با کیفیت بالا",
                        "image_url": "https://dkstatics-public.digikala.com/digikala-products/f9d556a68cc4a507cc80981935cf68ae2e3d7711_1690028248.jpg"
                    },
                    {
                        "title": "موبایل",
                        "price": 5000000.0,
                        "description": "موبایل هوشمند",
                        "image_url": "https://dkstatics-public.digikala.com/digikala-products/dcd6e4e843bea3f070614a95d27b1181fdf23008_1727276123.jpg"
                    },
                    {
                        "title": "تلویزیون",
                        "price": 15000000.0,
                        "description": "تلویزیون ال ای دی",
                        "image_url": "https://dkstatics-public.digikala.com/digikala-products/b201d440b264e5f4a25a146377e3aaf7c87558d4_1726651996.jpg"
                    },
                    {
                        "title": "کنسول بازی",
                        "price": 12000000.0,
                        "description": "کنسول بازی با بهترین کیفیت",
                        "image_url": "https://dkstatics-public.digikala.com/digikala-products/664d3783527b060deb7d4eedb71b5ce283adc598_1611391559.jpg"
                    }
                ]
            },
            {
                "category": "لوازم التحریر",
                "products": [
                    {
                        "title": "مداد مشکی فابر کاستل",
                        "price": 2000.0,
                        "description": "مداد مشکی با کیفیت",
                        "image_url": "https://dkstatics-public.digikala.com/digikala-products/1f4b067f444f0101df3911aa55a3cc7c5ad8b8fd_1694008897.jpg"
                    },
                    {
                        "title": "مداد رنگی فابر کاستل",
                        "price": 5000.0,
                        "description": "مداد رنگی با کیفیت",
                        "image_url": "https://dkstatics-public.digikala.com/digikala-products/121562183.jpg"
                    },
                    {
                        "title": "دفترچه",
                        "price": 1500.0,
                        "description": "دفترچه یادداشت با کیفیت",
                        "image_url": "https://dkstatics-public.digikala.com/digikala-products/689bbbcd2076edcb2397f91a155a06b9a76e2d7e_1667988058.jpg"
                    },
                    {
                        "title": "پاک کن",
                        "price": 1000.0,
                        "description": "پاک کن با کیفیت",
                        "image_url": "https://dkstatics-public.digikala.com/digikala-products/03787e85d045a6fb3678080f72148c356430c251_1719736578.jpg"
                    }
                ]
            },
            {
                "category": "پوشاک",
                "products": [
                    {
                        "title": "هودی",
                        "price": 80000.0,
                        "description": "هودی راحت و شیک",
                        "image_url": "https://dkstatics-public.digikala.com/digikala-products/afb4bb177473843c5065b2d6b197833e4350126c_1605037323.jpg"
                    },
                    {
                        "title": "کلاه",
                        "price": 15000.0,
                        "description": "کلاه مناسب فصل سرد",
                        "image_url": "https://dkstatics-public.digikala.com/digikala-products/320cc5bbdf585c58dd7ade496e03045370f09886_1658044548.jpg"
                    },
                    {
                        "title": "دستکش",
                        "price": 12000.0,
                        "description": "دستکش گرم و نرم",
                        "image_url": "https://dkstatics-public.digikala.com/digikala-products/4945770.jpg"
                    },
                    {
                        "title": "شلوار",
                        "price": 40000.0,
                        "description": "شلوار جین با کیفیت",
                        "image_url": "https://dkstatics-public.digikala.com/digikala-products/9422faeae7b975cae61e556db1ce6d374759a67a_1722251417.jpg"
                    }
                ]
            },
            {
                "category": "لوازم ورزشی",
                "products": [
                    {
                        "title": "کفش ورزشی",
                        "price": 15000.0,
                        "description": "کفش ورزشی با کیفیت",
                        "image_url": "https://dkstatics-public.digikala.com/digikala-products/4cca7ed89c9b20e730a0f278a906256d0b5af0a7_1674646352.jpg"
                    },
                    {
                        "title": "حوله",
                        "price": 20000.0,
                        "description": "حوله نرم و راحت",
                        "image_url": "https://dkstatics-public.digikala.com/digikala-products/868125.jpg"
                    },
                    {
                        "title": "عینک شنا",
                        "price": 25000.0,
                        "description": "عینک شنا با کیفیت",
                        "image_url": "https://dkstatics-public.digikala.com/digikala-products/5362772.jpg"
                    },
                    {
                        "title": "دمبل",
                        "price": 50000.0,
                        "description": "دمبل با کیفیت",
                        "image_url": "https://dkstatics-public.digikala.com/digikala-products/3987030.jpg"
                    }
                ]
            }
        ]
    }
    return jsonify(response)

if __name__ == '__main__':
    app.run(debug=True)
